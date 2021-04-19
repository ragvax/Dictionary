package com.ragvax.dictionary.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RecentQuery::class], version = 1)
abstract class DictionaryDatabase : RoomDatabase() {

    abstract fun dictionaryDao(): RecentQueryDao

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
                    ).build()
                    INSTANCE = instance
                    instance
                }
        }
    }
}