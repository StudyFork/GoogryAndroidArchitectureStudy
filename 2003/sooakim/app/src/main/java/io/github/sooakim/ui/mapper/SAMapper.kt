package io.github.sooakim.ui.mapper

import io.github.sooakim.domain.model.SAModel
import io.github.sooakim.ui.model.SAViewModel

interface SAMapper<M : SAModel, VM : SAViewModel> {
    fun mapToViewModel(from: M): VM

    fun mapToModel(from: VM): M
}