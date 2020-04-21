package io.github.sooakim.ui.mapper

import io.github.sooakim.domain.model.SAModel
import io.github.sooakim.ui.model.SAPresentation

interface SAPresentationMapper<in M : SAModel, out P : SAPresentation> {
    fun mapToPresentation(from: M): P
}