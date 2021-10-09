package com.ragvax.dictionary.data.source.local

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val recentQueryDao: RecentQueryDao,
) {

    suspend fun insertQuery(recentQuery: RecentQueryEntity) = recentQueryDao.insertQuery(recentQuery)

    suspend fun deleteQuery(recentQuery: RecentQueryEntity) = recentQueryDao.deleteQuery(recentQuery)

    fun getQueriesWithLimit(limit: Int): Flow<List<RecentQueryEntity>> = recentQueryDao.getQueriesWithLimit(limit)
}