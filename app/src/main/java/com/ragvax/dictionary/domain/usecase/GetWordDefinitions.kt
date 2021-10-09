package com.ragvax.dictionary.domain.usecase

import com.ragvax.dictionary.domain.model.WordDefinition
import com.ragvax.dictionary.domain.repository.IDefinitionRepository
import com.ragvax.dictionary.utils.Resource
import javax.inject.Inject

class GetWordDefinitions @Inject constructor(
    private val repository: IDefinitionRepository,
) {
    suspend operator fun invoke(word: String): Resource<WordDefinition> {
        return repository.getWordDefinitions(word)
    }
}