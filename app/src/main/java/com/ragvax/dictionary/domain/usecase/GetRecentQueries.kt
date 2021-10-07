package com.ragvax.dictionary.domain.usecase

import com.ragvax.dictionary.data.source.local.RecentQuery
import com.ragvax.dictionary.domain.repository.IDefinitionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecentQueries @Inject constructor(
    private val repository: IDefinitionRepository,
) {
    operator fun invoke(): Flow<List<RecentQuery>> {
        return repository.getRecentWordQueries()
    }
}