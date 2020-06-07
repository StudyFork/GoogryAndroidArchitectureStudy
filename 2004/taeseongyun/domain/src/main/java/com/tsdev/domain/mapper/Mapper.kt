package com.tsdev.domain.mapper

interface Mapper<T, S> {
    fun toData(data: T): S
}