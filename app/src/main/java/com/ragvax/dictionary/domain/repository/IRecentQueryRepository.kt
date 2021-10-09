package com.ragvax.dictionary.domain.repository

import com.ragvax.dictionary.data.source.local.RecentQueryEntity
import com.ragvax.dictionary.domain.model.RecentQuery
import kotlinx.coroutines.flow.Flow

interface IRecentQueryRepository {

    fun getRecentWordQueries(): Flow<List<RecentQuery>>

    suspend fun insertRecentWordQuery(word: String)

    suspend fun deleteRecentWordQuery(query: RecentQuery)
}
