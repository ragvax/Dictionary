package com.ragvax.dictionary.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DefinitionService {

    @GET("entries/en_US/{word}")
    suspend fun fetchWordDefinition(@Path("word") word: String): Response<List<Word>>
}