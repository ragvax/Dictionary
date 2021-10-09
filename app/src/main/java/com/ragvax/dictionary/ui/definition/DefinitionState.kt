package com.ragvax.dictionary.ui.definition

import com.ragvax.dictionary.domain.model.WordDefinition

sealed class DefinitionState {
    data class Success(val definition: WordDefinition) : DefinitionState()
    data class Failure(val errorTitle: String, val errorMsg: String) : DefinitionState()
    object Loading : DefinitionState()
    object Empty : DefinitionState()
}