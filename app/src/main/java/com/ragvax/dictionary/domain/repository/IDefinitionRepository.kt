package com.ragvax.dictionary.domain.repository

import com.ragvax.dictionary.data.source.remote.Word
import com.ragvax.dictionary.data.source.local.RecentQuery
import com.ragvax.dictionary.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IDefinitionRepository {

    suspend fun getWordDefinitions(word: String): Resource<Word>

    fun getRecentWordQueries(): Flow<List<RecentQuery>>

    suspend fun insertRecentWordQuery(word: String)

    suspend fun deleteRecentWordQuery(query: RecentQuery)
}