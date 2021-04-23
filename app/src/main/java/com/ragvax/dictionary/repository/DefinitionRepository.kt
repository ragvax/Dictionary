package com.ragvax.dictionary.repository

import com.ragvax.dictionary.data.DefinitionService
import com.ragvax.dictionary.data.Word
import com.ragvax.dictionary.utils.Resource
import okio.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefinitionRepository @Inject constructor(
    private val definitionService: DefinitionService
) {
    suspend fun fetchDefinitions(word: String): Resource<List<Word>> {
        return try {
            val response = definitionService.fetchWordDefinition(word)
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
            Resource.Error("An Error occurred while trying to fetch data from the server.")
        } catch (e: IOException) {
            Resource.Error(e.message ?: "Network Error")
        }
    }
}