package io.github.sooakim.data.mapper

import io.github.sooakim.data.model.SAData
import io.github.sooakim.domain.model.SAModel

internal interface SADataMapper<in D : SAData, out M : SAModel> {
    fun mapToModel(from: D): M
}