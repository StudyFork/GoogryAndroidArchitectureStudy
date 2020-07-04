package com.tsdev.domain.mapper

interface Mapper<E, T> {
    fun toData(data: E): T
}