package io.github.sooakim.data.local.mapper

import io.github.sooakim.data.model.SAEntity
import io.github.sooakim.data.local.model.SAEntity as LocalModel

interface SALocalMapper<L : LocalModel, E : SAEntity> {
    fun mapToLocal(from: E): L

    fun mapToEntity(from: L): E
}