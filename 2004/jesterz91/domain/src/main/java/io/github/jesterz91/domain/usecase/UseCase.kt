package io.github.jesterz91.domain.usecase

abstract class UseCase<T, in Params> {

    abstract fun buildUseCase(params: Params): T

    operator fun invoke(params: Params): T = buildUseCase(params)
}