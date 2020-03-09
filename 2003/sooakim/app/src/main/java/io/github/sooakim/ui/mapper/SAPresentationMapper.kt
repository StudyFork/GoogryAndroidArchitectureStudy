package io.github.sooakim.ui.mapper

import io.github.sooakim.domain.model.SAModel
import io.github.sooakim.ui.model.SAPresentation

interface SAPresentationMapper<M : SAModel, P : SAPresentation> {
    fun mapToPresentation(from: M): P

    fun mapToModel(from: P): M
}