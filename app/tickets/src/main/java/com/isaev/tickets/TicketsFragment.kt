package com.isaev.tickets

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class TicketsFragment : Fragment(R.layout.fragment_tickets) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val where = requireArguments().getString(ARG_WHERE).orEmpty()
        val from = requireArguments().getString(ARG_FROM).orEmpty()

        val whereView = view.findViewById<TextView>(R.id.where_city)
        val fromView = view.findViewById<TextView>(R.id.from_city)

        whereView.text = where
        fromView.text = from

        view.findViewById<ImageButton>(R.id.swap_button).setOnClickListener {
            val fromCurrent = fromView.text.toString()
            val whereCurrent = whereView.text.toString()

            whereView.text = fromCurrent
            fromView.text = whereCurrent
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