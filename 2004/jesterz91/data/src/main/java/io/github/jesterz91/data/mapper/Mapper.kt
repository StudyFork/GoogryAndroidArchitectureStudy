package io.github.jesterz91.data.mapper

interface Mapper<M, E> {

    fun mapToDomain(entity: M): E
}