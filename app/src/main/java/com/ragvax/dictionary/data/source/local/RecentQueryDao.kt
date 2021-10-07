package com.ragvax.dictionary.data.source.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentQueryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuery(recentQuery: RecentQuery)

    @Delete
    suspend fun deleteQuery(recentQuery: RecentQuery)

    @Query("SELECT * FROM recent_query ORDER BY time_date DESC LIMIT :limit")
    fun getQueriesWithLimit(limit: Int): Flow<List<RecentQuery>>

    @Query("DELETE FROM recent_query")
    suspend fun clear()
}