package io.github.sooakim.data.local.mapper

import io.github.sooakim.data.model.SAData
import io.github.sooakim.data.local.model.SAEntity as LocalModel

interface SALocalMapper<L : LocalModel, D : SAData> {
    fun mapToLocal(from: D): L

    fun mapToData(from: L): D
}