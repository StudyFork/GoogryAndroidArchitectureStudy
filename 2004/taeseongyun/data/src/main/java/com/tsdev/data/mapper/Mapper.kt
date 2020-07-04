package com.tsdev.data.mapper

interface Mapper<E, T> {
    fun toData(item: E): T
}