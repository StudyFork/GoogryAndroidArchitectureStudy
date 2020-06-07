package com.kyudong.data.mapper

interface Mapper<E, M> {
    fun toData(domain: E): M
    fun toDomain(data: M): E
}