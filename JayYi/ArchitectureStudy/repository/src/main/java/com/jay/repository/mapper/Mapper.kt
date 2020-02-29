package com.jay.repository.mapper

interface Mapper<T, H> {
    fun map(input: T): H

    fun reverseMap(output: H): T
}