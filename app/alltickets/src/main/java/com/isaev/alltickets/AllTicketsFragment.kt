package com.isaev.alltickets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.isaev.alltickets.databinding.FragmentAllTicketsBinding
import com.isaev.common.BackController
import java.text.SimpleDateFormat
import java.util.Date

class AllTicketsFragment : Fragment(R.layout.fragment_all_tickets) {


    private var _binding: FragmentAllTicketsBinding? = null
    private val binding: FragmentAllTicketsBinding get() = _binding!!

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