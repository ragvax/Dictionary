package com.ragvax.dictionary.domain.usecase

import com.ragvax.dictionary.domain.model.RecentQuery
import com.ragvax.dictionary.domain.repository.IRecentQueryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecentQueries @Inject constructor(
    private val repository: IRecentQueryRepository,
) {
    operator fun invoke(): Flow<List<RecentQuery>> {
        return repository.getRecentWordQueries()
    }
}