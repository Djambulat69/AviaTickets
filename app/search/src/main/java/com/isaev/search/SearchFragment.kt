package com.isaev.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.isaev.search.databinding.FragmentSearchBinding

class SearchFragment : BottomSheetDialogFragment(R.layout.fragment_search) {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            fromInput.setText(
                requireArguments().getString(ARG_WHERE)
            )

            anywhereHint.setOnClickListener {
                pickCity(getString(R.string.anywhere))
            }

            stambulHint.setOnClickListener {
                pickCity(getString(R.string.stambul))
            }
            sochiHint.setOnClickListener {
                pickCity(getString(R.string.sochi))
            }
            phuketHint.setOnClickListener {
                pickCity(getString(R.string.phuket))
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun pickCity(city: String) {
        (requireContext() as? SearchResultListener)?.onCityPicked(city)
        dismiss()
    }


    interface SearchResultListener {
        fun onCityPicked(city: String)
    }

    companion object {
        private const val ARG_WHERE = "arg where"

        fun newInstance(where: String) = SearchFragment().apply {
            arguments = bundleOf(ARG_WHERE to where)
        }
    }
}