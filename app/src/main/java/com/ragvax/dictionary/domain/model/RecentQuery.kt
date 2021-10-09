package com.ragvax.dictionary.domain.model

data class RecentQuery(
    val queryText: String,
    val timeDate: Long = System.currentTimeMillis()
) {
    companion object {
        val empty = RecentQuery("",0)
    }
}
