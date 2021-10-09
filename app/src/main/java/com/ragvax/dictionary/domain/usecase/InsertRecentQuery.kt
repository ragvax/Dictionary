package com.ragvax.dictionary.domain.usecase

import com.ragvax.dictionary.domain.repository.IDefinitionRepository
import com.ragvax.dictionary.domain.repository.IRecentQueryRepository
import javax.inject.Inject

class InsertRecentQuery @Inject constructor(
    private val repository: IRecentQueryRepository,
) {
    suspend operator fun invoke(word: String) {
        repository.insertRecentWordQuery(word)
    }
}