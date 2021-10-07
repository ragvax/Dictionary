package com.ragvax.dictionary.ui.definition

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ragvax.dictionary.data.source.remote.Word
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
                val data = result.data
                if (data != null) {
                    _definitionFlow.value = DefinitionState.Success(data)
                    state.set<Word>("state", data)
                    insertRecent(word)
                }
            }
            is Resource.Error -> _definitionFlow.value = DefinitionState.Failure(null,result.msg ?: "Resource: Error")
        }
    }

    private suspend fun getResult(word: String): Resource<Word> = if (state.get<Word>("state") != null) {
        Resource.Success(state.get<Word>("state")!!)
    } else {
        getWordDefinitions(word)
    }

    private fun insertRecent(word: String) = viewModelScope.launch(Dispatchers.IO) {
        insertRecentQuery(word)
    }
}