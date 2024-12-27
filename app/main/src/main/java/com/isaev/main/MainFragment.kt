package com.isaev.main

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.google.android.material.textfield.TextInputEditText
import com.isaev.search.SearchFragment
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val flyCardsRecycler = view.findViewById<RecyclerView>(R.id.fly_cards_recycler)

        val flyCardAdapter = FlyCardAdapter()

        flyCardsRecycler.adapter = flyCardAdapter

        flyCardsRecycler.addItemDecoration(object : ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
            ) {
                outRect.left = parent.context.resources.getDimensionPixelOffset(
                    if (parent.getChildAdapterPosition(view) == 0) R.dimen.fly_card_small_offset
                    else R.dimen.fly_card_big_offset
                )
                outRect.right = parent.context.resources.getDimensionPixelOffset(
                    if (parent.getChildAdapterPosition(view) == parent.adapter!!.itemCount - 1) R.dimen.fly_card_small_offset
                    else R.dimen.fly_card_big_offset
                )
            }
        })

        val whereEditText = view.findViewById<TextInputEditText>(R.id.where_input)
        whereEditText.setOnClickListener {
            SearchFragment().show(parentFragmentManager, null)
        }

        with(viewLifecycleOwner.lifecycleScope) {
            launch {
                viewModel.musicAirLinesState.filterNotNull().collect { offers ->
                    flyCardAdapter.submitList(offers)
                }
            }
        }
    }

    fun pickWhereCity(city: String) {
        val fromEditText = requireView().findViewById<TextInputEditText>(R.id.from_input)

        if (fromEditText.text.toString() == "") {
            fromEditText.setText(getString(com.isaev.network.R.string.moscow))
        }

        (requireContext() as? TicketsOpener)?.openTickets(city, fromEditText.text.toString())
    }

    companion object {
        const val TAG = "MainFragment"
    }
}