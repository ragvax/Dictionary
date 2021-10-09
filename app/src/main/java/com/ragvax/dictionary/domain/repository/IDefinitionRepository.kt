package com.ragvax.dictionary.domain.repository

import com.ragvax.dictionary.domain.model.WordDefinition
import com.ragvax.dictionary.utils.Resource

interface IDefinitionRepository {

    suspend fun getWordDefinitions(word: String): Resource<WordDefinition>
}