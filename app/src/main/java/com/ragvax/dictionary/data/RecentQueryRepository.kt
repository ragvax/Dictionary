package com.ragvax.dictionary.data

import com.ragvax.dictionary.data.source.local.LocalDataSource
import com.ragvax.dictionary.data.source.local.RecentQueryEntity
import com.ragvax.dictionary.data.source.local.RecentQueryMapper
import com.ragvax.dictionary.domain.model.RecentQuery
import com.ragvax.dictionary.domain.repository.IRecentQueryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecentQueryRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val RecentQueryMapper: RecentQueryMapper,
) : IRecentQueryRepository {

    override fun getRecentWordQueries(): Flow<List<RecentQuery>> {
        return localDataSource.getQueriesWithLimit(10).map { recentQueryList ->
            RecentQueryMapper.mapFromEntities(recentQueryList)
        }
    }

    override suspend fun insertRecentWordQuery(word: String) {
        localDataSource.insertQuery(RecentQueryEntity(word))
    }

    override suspend fun deleteRecentWordQuery(query: RecentQuery) {
        val recentQueryEntity = RecentQueryMapper.mapToEntity(query)
        localDataSource.deleteQuery(recentQueryEntity)
    }
}