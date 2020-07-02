package io.github.jesterz91.study.mapper

interface Mapper<M, E> {

    fun mapToPresentation(domain: M): E
}