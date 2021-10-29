package com.ragvax.dictionary.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RecentQueryEntity::class], version = 1, exportSchema = false)
abstract class DictionaryDatabase : RoomDatabase() {

    abstract fun recentQueryDao(): RecentQueryDao

    companion object {
        @Volatile
        private var INSTANCE: DictionaryDatabase? = null

        fun getDatabase(
            context: Context,
        ): DictionaryDatabase {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        DictionaryDatabase::class.java,
                        "dictionary_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                    instance
                }
        }
    }
}