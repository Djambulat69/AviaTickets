package com.isaev.alltickets

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.isaev.alltickets.databinding.FragmentAllTicketsBinding
import com.isaev.common.BackController
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject
import javax.inject.Provider

class AllTicketsFragment : Fragment(R.layout.fragment_all_tickets) {

    private lateinit var allTicketsComponent: AllTicketsComponent

    private var _binding: FragmentAllTicketsBinding? = null
    private val binding: FragmentAllTicketsBinding get() = _binding!!

    @Inject
    lateinit var viewModelProvider: Provider<AllTicketsViewModel>

    private val viewModel: AllTicketsViewModel by viewModels {
        viewModelFactory {
            addInitializer(AllTicketsViewModel::class) { viewModelProvider.get() }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        allTicketsComponent = (requireContext().applicationContext as AllTicketsComponentProvider)
            .provideAllTicketsComponent()

        allTicketsComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllTicketsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val from = requireArguments().getString(ARG_FROM)
        val where = requireArguments().getString(ARG_WHERE)

        binding.cities.text = "$from-$where"

        val formatter =
            SimpleDateFormat("d MMMM, 1 пассажир", resources.configuration.locales.get(0))
        binding.date.text = formatter.format(Date())


        binding.backButton.setOnClickListener {
            (requireContext() as? BackController)?.goBack()
        }


        val ticketsAdapter = TicketsAdapter()
        binding.ticketsRecycler.adapter = ticketsAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.ticketsState.collect { tickets ->
                ticketsAdapter.submitList(tickets)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance(where: String, from: String) = AllTicketsFragment().apply {
            arguments = bundleOf(
                ARG_WHERE to where,
                ARG_FROM to from
            )
        }

        private const val ARG_WHERE = "arg where"
        private const val ARG_FROM = "arg from"
    }
}