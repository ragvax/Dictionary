package com.ragvax.dictionary.di

import com.ragvax.dictionary.domain.repository.IDefinitionRepository
import com.ragvax.dictionary.domain.usecase.DeleteRecentQuery
import com.ragvax.dictionary.domain.usecase.GetRecentQueries
import com.ragvax.dictionary.domain.usecase.GetWordDefinitions
import com.ragvax.dictionary.domain.usecase.InsertRecentQuery
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetWordDefinitions(repository: IDefinitionRepository): GetWordDefinitions {
        return GetWordDefinitions(repository)
    }

    @Provides
    @Singleton
    fun provideGetRecentQuery(repository: IDefinitionRepository): GetRecentQueries {
        return GetRecentQueries(repository)
    }

    @Provides
    @Singleton
    fun provideInsertRecentQuery(repository: IDefinitionRepository): InsertRecentQuery {
        return InsertRecentQuery(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteRecentQuery(repository: IDefinitionRepository): DeleteRecentQuery {
        return DeleteRecentQuery(repository)
    }
}