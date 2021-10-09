package com.ragvax.dictionary.data.source.local

import com.ragvax.dictionary.domain.model.RecentQuery
import com.ragvax.dictionary.utils.Mapper
import javax.inject.Inject

class RecentQueryMapper @Inject constructor() : Mapper<RecentQueryEntity, RecentQuery> {
    override fun mapFromEntity(input: RecentQueryEntity): RecentQuery {
        return RecentQuery(
            queryText = input.queryText,
            timeDate = input.timeDate
        )
    }

    fun mapFromEntities(input: List<RecentQueryEntity>): List<RecentQuery> {
        return input.map {
            mapFromEntity(it)
        }
    }

    override fun mapToEntity(input: RecentQuery): RecentQueryEntity {
        return RecentQueryEntity(
            queryText = input.queryText,
            timeDate = input.timeDate
        )
    }
}