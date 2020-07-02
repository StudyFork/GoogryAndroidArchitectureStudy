package io.github.jesterz91.data.mapper

internal interface Mapper<M, E> {

    fun mapToDomain(entity: M): E
}