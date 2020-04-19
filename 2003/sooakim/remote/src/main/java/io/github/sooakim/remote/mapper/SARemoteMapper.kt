package io.github.sooakim.remote.mapper

import io.github.sooakim.data.model.SAData

internal interface SARemoteMapper<R : io.github.sooakim.remote.model.SAModel, D : SAData> {
    fun mapToRemote(from: D): R

    fun mapToData(from: R): D
}