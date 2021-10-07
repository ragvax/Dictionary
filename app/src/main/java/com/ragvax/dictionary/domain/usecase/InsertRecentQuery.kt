package com.ragvax.dictionary.domain.usecase

import com.ragvax.dictionary.domain.repository.IDefinitionRepository
import javax.inject.Inject

class InsertRecentQuery @Inject constructor(
    private val repository: IDefinitionRepository,
) {
    suspend operator fun invoke(word: String) {
        repository.insertRecentWordQuery(word)
    }
}