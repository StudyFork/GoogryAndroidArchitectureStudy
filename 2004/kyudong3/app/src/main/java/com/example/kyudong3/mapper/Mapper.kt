package com.example.kyudong3.mapper

interface Mapper<E, M> {
    fun toData(domain: E): M
    fun toDomain(data: M): E
}