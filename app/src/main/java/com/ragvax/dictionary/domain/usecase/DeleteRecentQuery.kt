package com.ragvax.dictionary.domain.usecase

import com.ragvax.dictionary.data.source.local.RecentQuery
import com.ragvax.dictionary.domain.repository.IDefinitionRepository
import javax.inject.Inject

class DeleteRecentQuery @Inject constructor(
    private val repository: IDefinitionRepository,
) {
    suspend operator fun invoke(query: RecentQuery) {
        repository.deleteRecentWordQuery(query)
    }
}