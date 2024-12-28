package com.isaev.main

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.isaev.main.databinding.FragmentMainBinding
import com.isaev.search.SearchFragment
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var mainComponent: MainComponent

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!

    @Inject
    lateinit var viewModelFactoryProvider: Provider<MainViewModel.Factory>

    private val viewModel: MainViewModel by viewModels { viewModelFactoryProvider.get() }


    override fun onAttach(context: Context) {
        super.onAttach(context)

        mainComponent = (requireContext().applicationContext as MainComponentProvider).provideMainComponent()
        mainComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val flyCardAdapter = FlyCardAdapter()

        binding.flyCardsRecycler.adapter = flyCardAdapter

        binding.flyCardsRecycler.addItemDecoration(object : ItemDecoration() {
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


        binding.whereInput.setOnClickListener {
            if (binding.fromInput.text.toString() == "") {
                binding.fromInput.setText(getString(com.isaev.common.R.string.moscow))
            }

            SearchFragment.newInstance(binding.fromInput.text?.toString().orEmpty())
                .show(parentFragmentManager, null)
        }

        with(viewLifecycleOwner.lifecycleScope) {
            launch {
                viewModel.musicAirLinesState.filterNotNull().collect { offers ->
                    flyCardAdapter.submitList(offers)
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    fun pickWhereCity(city: String) {
        if (binding.fromInput.text.toString() == "") {
            binding.fromInput.setText(getString(com.isaev.common.R.string.moscow))
        }

        (requireContext() as? TicketsOpener)?.openTickets(city, binding.fromInput.text.toString())
    }

    companion object {
        const val TAG = "MainFragment"
    }
}