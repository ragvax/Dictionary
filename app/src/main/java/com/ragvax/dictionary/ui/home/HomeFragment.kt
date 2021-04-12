package com.ragvax.dictionary.ui.home

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ragvax.dictionary.R
import com.ragvax.dictionary.databinding.FragmentHomeBinding
import com.ragvax.dictionary.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        initView()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.homeEvent.collectLatest { event ->
                when (event) {
                    is HomeEvent.NavigateToDefinition -> {
                        val action = HomeFragmentDirections.actionHomeFragmentToDefinitionFragment(event.query)
                        findNavController().navigate(action)
                        hideKeyboard()
                    }
                }
            }
        }
    }

    private fun initView() {
        binding.tvSearch.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_SEARCH && !textView.text.toString().isNullOrEmpty()) {
                onSearchAction(textView.text.toString())
                true
            } else {
                false
            }
        }
    }

    private fun onSearchAction(query: String) {
        viewModel.onButtonClick(query)
    }
}