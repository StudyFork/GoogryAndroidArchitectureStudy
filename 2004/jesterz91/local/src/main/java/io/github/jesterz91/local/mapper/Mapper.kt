package io.github.jesterz91.local.mapper

interface Mapper<M, E> {

    fun mapToEntity(local: M): E

    fun mapToLocal(entity: E): M
}