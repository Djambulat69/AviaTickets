package com.isaev.main

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.google.android.material.textfield.TextInputEditText
import com.isaev.common.Network
import com.isaev.search.SearchFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : Fragment(R.layout.fragment_main) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val flyCardsRecycler = view.findViewById<RecyclerView>(R.id.fly_cards_recycler)

        val flyCardAdapter = FlyCardAdapter()

        flyCardsRecycler.adapter = flyCardAdapter

        flyCardsRecycler.addItemDecoration(
            object : ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.left = parent.context.resources.getDimensionPixelOffset(
                        if (parent.getChildAdapterPosition(view) == 0)
                            R.dimen.fly_card_small_offset
                        else
                            R.dimen.fly_card_big_offset
                    )
                    outRect.right = parent.context.resources.getDimensionPixelOffset(
                        if (parent.getChildAdapterPosition(view) == parent.adapter!!.itemCount - 1)
                            R.dimen.fly_card_small_offset
                        else
                            R.dimen.fly_card_big_offset
                    )
                }
            }
        )

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val offers = Network.getMain()

                withContext(Dispatchers.Main) {
                    flyCardAdapter.submitList(offers)
                }
            }
        }

        view.findViewById<TextInputEditText>(R.id.where_input).setOnClickListener {
            SearchFragment().show(parentFragmentManager, null)
        }
    }

}