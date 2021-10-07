package com.ragvax.dictionary.data

import com.ragvax.dictionary.data.source.local.LocalDataSource
import com.ragvax.dictionary.data.source.local.RecentQuery
import com.ragvax.dictionary.data.source.remote.RemoteDataSource
import com.ragvax.dictionary.data.source.remote.Word
import com.ragvax.dictionary.domain.repository.IDefinitionRepository
import com.ragvax.dictionary.utils.Resource
import kotlinx.coroutines.flow.Flow
import okio.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefinitionRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
): IDefinitionRepository {

    override suspend fun getWordDefinitions(word: String): Resource<Word> {
        return try {
            val response = remoteDataSource.fetchWordDefinition(word)
            if (response.isSuccessful) {
                val result = response.body()?.get(0)
                if (result != null) {
                    Resource.Success(result)
                } else {
                    Resource.Error("Whoops","Server returned an empty result")
                }
            } else {
                Resource.Error(
                    "No definitions found",
                    "Sorry, we couldn't find definitions for the word you were looking for."
                )
            }
        } catch (e: Exception) {
            Resource.Error("Network Error", "An error occurred while trying to fetch data from the server. Please check you internet connection.")
        } catch (e: IOException) {
            Resource.Error("Network Error", e.message ?: "Network Error")
        }
    }

    override fun getRecentWordQueries(): Flow<List<RecentQuery>> {
        return localDataSource.getQueriesWithLimit(10)
    }

    override suspend fun insertRecentWordQuery(word: String) {
        localDataSource.insertQuery(RecentQuery(word))
    }

    override suspend fun deleteRecentWordQuery(query: RecentQuery) {
        localDataSource.deleteQuery(query)
    }
}