package com.ragvax.dictionary.data.source.local

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val recentQueryDao: RecentQueryDao,
) {

    suspend fun insertQuery(recentQuery: RecentQuery) = recentQueryDao.insertQuery(recentQuery)

    suspend fun deleteQuery(recentQuery: RecentQuery) = recentQueryDao.deleteQuery(recentQuery)

    fun getQueriesWithLimit(limit: Int): Flow<List<RecentQuery>> = recentQueryDao.getQueriesWithLimit(limit)
}