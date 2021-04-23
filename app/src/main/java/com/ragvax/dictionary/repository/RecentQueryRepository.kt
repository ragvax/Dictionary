package com.ragvax.dictionary.repository

import com.ragvax.dictionary.data.local.RecentQuery
import com.ragvax.dictionary.data.local.RecentQueryDao
import javax.inject.Inject

class RecentQueryRepository @Inject constructor(private val recentQueryDao: RecentQueryDao) {

    fun getRecentQueries() = recentQueryDao.getQueriesWithLimit(10)

    suspend fun insertRecentQuery(word: String) {
        recentQueryDao.insertQuery(RecentQuery(word))
    }
}