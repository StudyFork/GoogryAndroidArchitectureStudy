package io.github.sooakim.local.mapper

import io.github.sooakim.data.model.SAData
import io.github.sooakim.local.model.SAEntity

internal interface SALocalMapper<L : SAEntity, D : SAData> {
    fun mapToLocal(from: D): L

    fun mapToData(from: L): D
}