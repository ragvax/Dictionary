package com.ragvax.dictionary.di

import com.ragvax.dictionary.data.DefinitionRepository
import com.ragvax.dictionary.domain.repository.IDefinitionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(definitionRepository: DefinitionRepository): IDefinitionRepository
}