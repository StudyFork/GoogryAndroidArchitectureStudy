package io.github.jesterz91.study.domain.usecase

abstract class UseCase<T, in Params> {

    abstract fun buildUseCase(params: Params): T

    operator fun invoke(params: Params): T = buildUseCase(params)
}