package com.ragvax.dictionary.data

import com.ragvax.dictionary.data.source.remote.DefinitionMapper
import com.ragvax.dictionary.data.source.remote.RemoteDataSource
import com.ragvax.dictionary.data.source.remote.WordDefinitionEntity
import com.ragvax.dictionary.domain.model.WordDefinition
import com.ragvax.dictionary.domain.repository.IDefinitionRepository
import com.ragvax.dictionary.utils.Resource
import okio.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefinitionRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val definitionMapper: DefinitionMapper,
): IDefinitionRepository {

    override suspend fun getWordDefinitions(word: String): Resource<WordDefinition> {
        return try {
            val response = remoteDataSource.fetchWordDefinition(word)
            if (response.isSuccessful) {
                val result = response.body()?.get(0)
                if (result != null) {
                    Resource.Success(mapResult(result))
                } else {
                    Resource.Error(GENERIC_ERROR, EMPTY_RESULT_MESSAGE)
                }
            } else {
                Resource.Error(DEFINITIONS_NOT_FOUND, DEFINITIONS_NOT_FOUND_MESSAGE)
            }
        } catch (throwable: Throwable) {
            when (throwable) {
                is Exception -> {
                    Resource.Error(NETWORK_ERROR, EXCEPTION_ERROR_MESSAGE)
                }
                is IOException -> {
                    Resource.Error(NETWORK_ERROR, throwable.message ?: NETWORK_ERROR)
                }
                else -> Resource.Error(ERROR, UNKNOWN_ERROR_MESSAGE)
            }
        }
    }

    private fun mapResult(result: WordDefinitionEntity): WordDefinition {
        return definitionMapper.mapFromEntity(result)
    }

    companion object {
        const val ERROR = "Error"
        const val GENERIC_ERROR = "Whoops"
        const val NETWORK_ERROR = "Network Error"
        const val DEFINITIONS_NOT_FOUND = "No definitions found"
        const val EMPTY_RESULT_MESSAGE = "Server returned an empty result"
        const val DEFINITIONS_NOT_FOUND_MESSAGE = "Sorry, we couldn't find definitions for the word you were looking for."
        const val EXCEPTION_ERROR_MESSAGE = "An error occurred while trying to fetch data from the server. Please check you internet connection."
        const val UNKNOWN_ERROR_MESSAGE = "Unknown error occurred"
    }
}