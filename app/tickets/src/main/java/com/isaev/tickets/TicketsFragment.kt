package com.isaev.tickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.isaev.common.TicketOffer
import com.isaev.common.formatPrice
import com.isaev.tickets.databinding.FragmentTicketsBinding
import com.isaev.tickets.databinding.TicketItemBinding
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class TicketsFragment : Fragment(R.layout.fragment_tickets) {

    private var _binding: FragmentTicketsBinding? = null
    private val binding: FragmentTicketsBinding get() = _binding!!

    private val colors = listOf(
        R.color.red,
        R.color.blue,
        R.color.white
    )

    private val viewModel: TicketsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTicketsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val where = requireArguments().getString(ARG_WHERE).orEmpty()
        val from = requireArguments().getString(ARG_FROM).orEmpty()

        binding.whereCity.text = where
        binding.fromCity.text = from

        binding.swapButton.setOnClickListener {
            val fromCurrent = binding.fromCity.text.toString()
            val whereCurrent = binding.whereCity.text.toString()

            binding.whereCity.text = fromCurrent
            binding.fromCity.text = whereCurrent
        }

        val dateFormatter =
            SimpleDateFormat("d MMM, EEEEEE", resources.configuration.locales.get(0))

        binding.dateOption.text = dateFormatter.format(Date())

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.ticketsState
                .filterNotNull()
                .collect {
                    showTickets(it)
                }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun showTickets(offers: List<TicketOffer>) {
        offers.forEachIndexed { i, offer ->
            val ticketBinding = TicketItemBinding.inflate(layoutInflater).apply {
                price.text = offer.price.value.formatPrice()
                airline.text = offer.title
                times.text = offer.timeRange.joinToString(" ")
            }

            ticketBinding.airlineImg.setBackgroundColor(
                resources.getColor(colors[i], requireContext().theme)
            )

            binding.races.addView(
                ticketBinding.root,
                LinearLayout.LayoutParams.MATCH_PARENT,
                resources.getDimensionPixelSize(R.dimen.ticket_item_height)
            )
        }

    }

    companion object {
        fun newInstance(where: String, from: String) = TicketsFragment().apply {
            arguments = bundleOf(
                ARG_WHERE to where,
                ARG_FROM to from
            )
        }

        const val ARG_WHERE = "arg where"
        const val ARG_FROM = "arg from"
    }

}