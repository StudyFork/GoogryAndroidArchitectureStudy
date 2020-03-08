package io.github.sooakim.data.remote.mapper

import io.github.sooakim.data.model.SAEntity
import io.github.sooakim.data.remote.model.SAModel

interface SARemoteMapper<R : SAModel, E : SAEntity> {
    fun mapToRemote(from: E): R

    fun mapToEntity(from: R): E
}