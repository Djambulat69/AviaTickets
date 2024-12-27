package com.isaev.search

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SearchFragment : BottomSheetDialogFragment(R.layout.fragment_search) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageView>(R.id.anywhere_hint).setOnClickListener {
            pickCity(getString(R.string.anywhere))
        }

        view.findViewById<View>(R.id.stambul_hint).setOnClickListener {
            pickCity(getString(R.string.stambul))
        }
        view.findViewById<View>(R.id.sochi_hint).setOnClickListener {
            pickCity(getString(R.string.sochi))
        }
        view.findViewById<View>(R.id.phuket_hint).setOnClickListener {
            pickCity(getString(R.string.phuket))
        }
    }

    private fun pickCity(city: String) {
        (requireContext() as? SearchResultListener)?.onCityPicked(city)
        dismiss()
    }


    interface SearchResultListener {
        fun onCityPicked(city: String)
    }
}