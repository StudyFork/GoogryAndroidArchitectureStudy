package io.github.jesterz91.remote.mapper

internal interface Mapper<M, E> {

    fun mapToEntity(remote: M): E
}