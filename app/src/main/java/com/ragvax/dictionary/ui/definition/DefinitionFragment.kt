package com.ragvax.dictionary.ui.definition

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ragvax.dictionary.R
import com.ragvax.dictionary.data.source.remote.Meaning
import com.ragvax.dictionary.data.source.remote.Word
import com.ragvax.dictionary.databinding.FragmentDefinitionBinding
import com.ragvax.dictionary.ui.definition.adapters.MeaningAdapter
import com.ragvax.dictionary.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DefinitionFragment : Fragment(R.layout.fragment_definition) {
    private val viewModel: DefinitionViewModel by viewModels()
    private val args: DefinitionFragmentArgs by navArgs()
    private var _binding: FragmentDefinitionBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDefinitionBinding.bind(view)
        val queryStr = args.query
        getWordDefinitions(queryStr)

        observeViewModel()
    }

    private fun getWordDefinitions(query: String) {
        viewModel.getDefinitions(query)
    }

    private fun setupAdapter(meanings: List<Meaning>) {
        binding.apply {
            rvMeaning.adapter = MeaningAdapter(meanings, requireContext())
            rvMeaning.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvMeaning.isNestedScrollingEnabled = false
            rvMeaning.setHasFixedSize(false)
        }
    }

    private fun observeViewModel() {
        viewModel.definitionFlow.collectWhileStarted(viewLifecycleOwner) { state ->
            when (state) {
                is DefinitionState.Success -> bindOnSuccess(state.definition)
                is DefinitionState.Failure -> bindOnFailure(state.errorTitle, state.errorMsg)
                is DefinitionState.Empty -> bindEmpty()
                is DefinitionState.Loading -> bindEmpty()
            }
        }
    }

    private fun bindOnSuccess(result: Word) {
        binding.apply {
            requireContext().also {
                it.hideViews(progressBar, tvErrorTitle, tvErrorMessage)
                it.showViews(tvWordTitle, tvPhonetic, tvDefinitionTitle)
            }
            tvWordTitle.text = result.word
            tvPhonetic.text = result.phonetics?.joinToString { it -> "${it.text}" }
            if (result.meanings != null) {
                rvMeaning.show()
                setupAdapter(result.meanings)
            } else rvMeaning.hide()
        }
    }

    private fun bindOnFailure(errorTitle: String?, errorMsg: String?) {
        binding.apply {
            requireContext().also {
                it.hideViews(progressBar, tvWordTitle, tvPhonetic, tvDefinitionTitle, rvMeaning)
                it.showViews(tvErrorTitle, tvErrorMessage)
            }
            if (errorTitle != null) tvErrorTitle.text = errorTitle
            if (errorMsg != null) tvErrorMessage.text = errorMsg
        }
    }

    private fun bindEmpty(){
        binding.apply {
            requireContext().hideViews(tvWordTitle, tvPhonetic, tvDefinitionTitle, rvMeaning, tvErrorTitle, tvErrorMessage)
            progressBar.show()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}