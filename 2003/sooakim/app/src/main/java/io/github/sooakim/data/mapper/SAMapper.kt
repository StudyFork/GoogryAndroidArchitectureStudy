package io.github.sooakim.data.mapper

import io.github.sooakim.data.model.SAEntity
import io.github.sooakim.domain.model.SAModel

interface SAMapper<E : SAEntity, M : SAModel> {
    fun mapToModel(from: E): M

    fun mapToEntity(from: M): E
}