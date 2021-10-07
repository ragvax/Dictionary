package com.ragvax.dictionary.data

import com.ragvax.dictionary.data.source.local.RecentQuery
import com.ragvax.dictionary.data.source.local.RecentQueryDao
import com.ragvax.dictionary.data.source.remote.DefinitionService
import com.ragvax.dictionary.data.source.remote.Word
import com.ragvax.dictionary.domain.repository.IDefinitionRepository
import com.ragvax.dictionary.utils.Resource
import kotlinx.coroutines.flow.Flow
import okio.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefinitionRepository @Inject constructor(
    private val remoteDataSource: DefinitionService,
    private val localDataSource: RecentQueryDao,
): IDefinitionRepository {

    override suspend fun getWordDefinitions(word: String): Resource<List<Word>> {
        return try {
            val response = remoteDataSource.fetchWordDefinition(word)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.ErrorGeneric(
                    "No definitions found",
                    "Sorry, we couldn't find definitions for the word you were looking for."
                )
            }
        } catch (e: Exception) {
            Resource.Error("An Error occurred while trying to fetch data from the server. Please check you internet connection. ")
        } catch (e: IOException) {
            Resource.Error(e.message ?: "Network Error")
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