package com.ragvax.dictionary.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recent_query")
data class RecentQueryEntity(

    @PrimaryKey
    @ColumnInfo(name = "query_text")
    val queryText: String,

    @ColumnInfo(name = "time_date")
    val timeDate: Long = System.currentTimeMillis()
)
