package com.ragvax.dictionary.di

import android.content.Context
import com.ragvax.dictionary.data.source.local.DictionaryDatabase
import com.ragvax.dictionary.data.source.local.RecentQueryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): DictionaryDatabase {
        return DictionaryDatabase.getDatabase(context)
    }

    @Provides
    fun provideRecentQueryDao(dictionaryDatabase: DictionaryDatabase): RecentQueryDao {
        return dictionaryDatabase.recentQueryDao()
    }
}