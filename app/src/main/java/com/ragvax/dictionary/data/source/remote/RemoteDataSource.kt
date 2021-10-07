package com.ragvax.dictionary.data.source.remote

import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: DefinitionService,
) {
    suspend fun fetchWordDefinition(word: String): Response<List<Word>> {
        return apiService.fetchWordDefinition(word)
    }
}