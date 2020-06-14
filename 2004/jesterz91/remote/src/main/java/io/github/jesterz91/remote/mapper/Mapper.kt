package io.github.jesterz91.remote.mapper

interface Mapper<M, E> {

    fun mapToEntity(remote: M): E
}