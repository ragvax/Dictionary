package com.ragvax.dictionary.ui.home

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ragvax.dictionary.R
import com.ragvax.dictionary.databinding.FragmentHomeBinding
import com.ragvax.dictionary.ui.home.adapters.RecentSearchesAdapter
import com.ragvax.dictionary.utils.collectWhileStarted
import com.ragvax.dictionary.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home),
    RecentSearchesAdapter.OnRecentSearchesItemClickListener {
    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recentSearchesAdapter: RecentSearchesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        recentSearchesAdapter = RecentSearchesAdapter(this)

        initView()
        observeViewModel()
    }

    private fun initView() {
        binding.apply {
            tvSearch.setOnEditorActionListener { textView, i, _ ->
                if (i == EditorInfo.IME_ACTION_SEARCH && textView.text.toString().isNotEmpty()) {
                    onSearchAction(textView.text.toString())
                    true
                } else {
                    false
                }
            }

            rvRecentSearches.adapter = recentSearchesAdapter
            rvRecentSearches.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val query = recentSearchesAdapter.currentList[viewHolder.adapterPosition]
                    viewModel.onQuerySwiped(query)
                }
            }).attachToRecyclerView(rvRecentSearches)
        }
    }

    private fun observeViewModel() {
        viewModel.homeEvent.collectWhileStarted(viewLifecycleOwner) { event ->
            when (event) {
                is HomeEvent.NavigateToDefinition -> {
                    val action = HomeFragmentDirections.actionHomeFragmentToDefinitionFragment(event.query)
                    findNavController().navigate(action)
                    hideKeyboard()
                }
                is HomeEvent.ShowDeleteNotificationToast -> {
                    Toast.makeText(context, "Recent query deleted", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.recentQueries.collect { recentQueries ->
                recentSearchesAdapter.submitList(recentQueries)
            }
        }
    }

    override fun onRecentSearchesItemClick(query: String) {
        viewModel.onButtonClick(query)
    }

    private fun onSearchAction(query: String) {
        viewModel.onButtonClick(query)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}