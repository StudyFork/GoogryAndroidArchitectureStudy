package io.github.jesterz91.study.domain.mapper

interface Mapper<E, M> {

    fun toData(domain: E): M

    fun toDomain(data: M): E
}