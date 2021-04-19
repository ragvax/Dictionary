package com.ragvax.dictionary.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ragvax.dictionary.data.Word

@Entity(tableName = "recent_query")
data class RecentQuery(

    @PrimaryKey
    @ColumnInfo(name = "query_text")
    val queryText: String,

    @ColumnInfo(name = "time_date")
    val timeDate: Long = System.currentTimeMillis()
)
