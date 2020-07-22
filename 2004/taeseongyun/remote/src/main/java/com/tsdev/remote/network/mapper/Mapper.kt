package com.tsdev.remote.network.mapper

interface Mapper<E, T> {
    fun toData(data: E): T

    fun fromData(data: T): E
}