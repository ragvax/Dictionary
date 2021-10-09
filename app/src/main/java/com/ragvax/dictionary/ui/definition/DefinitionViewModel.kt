package com.ragvax.dictionary.ui.definition

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ragvax.dictionary.domain.model.WordDefinition
import com.ragvax.dictionary.domain.usecase.GetWordDefinitions
import com.ragvax.dictionary.domain.usecase.InsertRecentQuery
import com.ragvax.dictionary.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DefinitionViewModel @Inject constructor(
    private val getWordDefinitions: GetWordDefinitions,
    private val insertRecentQuery: InsertRecentQuery,
    private val state: SavedStateHandle,
) : ViewModel() {

    private val _definitionFlow = MutableStateFlow<DefinitionState>(DefinitionState.Empty)
    val definitionFlow: StateFlow<DefinitionState> = _definitionFlow

    fun getDefinitions(word: String) = viewModelScope.launch(Dispatchers.IO) {
        _definitionFlow.value = DefinitionState.Loading
        when(val result = getResult(word)) {
            is Resource.Success -> {
                _definitionFlow.value = DefinitionState.Success(result.data)
                state.set<WordDefinition>("state", result.data)
                insertRecent(word)
            }
            is Resource.Error -> _definitionFlow.value = DefinitionState.Failure(result.title,result.message)
        }
    }

    private suspend fun getResult(word: String): Resource<WordDefinition> = if (state.get<WordDefinition>("state") != null) {
        Resource.Success(state.get<WordDefinition>("state")!!)
    } else {
        getWordDefinitions(word)
    }

    private fun insertRecent(word: String) = viewModelScope.launch(Dispatchers.IO) {
        insertRecentQuery(word)
    }
}