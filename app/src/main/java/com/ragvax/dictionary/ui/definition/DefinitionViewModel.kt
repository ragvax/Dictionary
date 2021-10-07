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

    private val _definitionFlow = MutableStateFlow<DefinitionEvent>(DefinitionEvent.Empty)
    val definitionFlow: StateFlow<DefinitionEvent> = _definitionFlow

    fun getDefinitions(word: String) = viewModelScope.launch(Dispatchers.IO) {
        _definitionFlow.value = DefinitionEvent.Loading
        when(val result = getResult(word)) {
            is Resource.Success -> {
                val data = result.data
                if (data != null) {
                    _definitionFlow.value = DefinitionEvent.Success(data[0])
                    state.set<Word>("state", data[0])
                    insertRecent(word)
                } else {
                    _definitionFlow.value = DefinitionEvent.Failure("Error", "Result: Error")
                }
            }
            is Resource.Error -> _definitionFlow.value = DefinitionEvent.Failure(null,result.msg ?: "Resource: Error")
            is Resource.ErrorGeneric -> _definitionFlow.value = DefinitionEvent.Failure(result.title, result.msg)
        }
    }

    private suspend fun getResult(word: String): Resource<List<Word>> = if (state.get<Word>("state") != null) {
        Resource.Success(listOf(state.get<Word>("state")!!))
    } else {
        getWordDefinitions(word)
    }

    private fun insertRecent(word: String) = viewModelScope.launch(Dispatchers.IO) {
        insertRecentQuery(word)
    }
}

sealed class DefinitionEvent {
    data class Success(val definition: Word) : DefinitionEvent()
    data class Failure(val errorTitle: String?, val errorMsg: String?) : DefinitionEvent()
    object Loading : DefinitionEvent()
    object Empty : DefinitionEvent()
}