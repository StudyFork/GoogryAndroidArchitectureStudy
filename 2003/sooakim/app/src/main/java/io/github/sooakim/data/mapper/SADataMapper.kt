package io.github.sooakim.data.mapper

import io.github.sooakim.data.model.SAData
import io.github.sooakim.domain.model.SAModel

interface SADataMapper<D : SAData, M : SAModel> {
    fun mapToModel(from: D): M

    fun mapToData(from: M): D
}