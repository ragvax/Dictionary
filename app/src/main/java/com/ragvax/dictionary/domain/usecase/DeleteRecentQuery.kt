package com.ragvax.dictionary.domain.usecase

import com.ragvax.dictionary.domain.model.RecentQuery
import com.ragvax.dictionary.domain.repository.IRecentQueryRepository
import javax.inject.Inject

class DeleteRecentQuery @Inject constructor(
    private val repository: IRecentQueryRepository,
) {
    suspend operator fun invoke(query: RecentQuery) {
        repository.deleteRecentWordQuery(query)
    }
}