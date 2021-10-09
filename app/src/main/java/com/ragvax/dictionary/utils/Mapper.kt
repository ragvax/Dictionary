package com.ragvax.dictionary.utils

interface Mapper<Entity, DomainModel> {
    fun mapFromEntity(input: Entity): DomainModel
    fun mapToEntity(input: DomainModel): Entity
}