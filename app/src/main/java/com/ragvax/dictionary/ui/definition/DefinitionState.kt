package com.ragvax.dictionary.ui.definition

import com.ragvax.dictionary.data.source.remote.Word

sealed class DefinitionState {
    data class Success(val definition: Word) : DefinitionState()
    data class Failure(val errorTitle: String?, val errorMsg: String?) : DefinitionState()
    object Loading : DefinitionState()
    object Empty : DefinitionState()
}