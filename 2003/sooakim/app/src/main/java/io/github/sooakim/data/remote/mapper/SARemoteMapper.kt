package io.github.sooakim.data.remote.mapper

import io.github.sooakim.data.model.SAData
import io.github.sooakim.data.remote.model.SAModel

interface SARemoteMapper<R : SAModel, D : SAData> {
    fun mapToRemote(from: D): R

    fun mapToData(from: R): D
}