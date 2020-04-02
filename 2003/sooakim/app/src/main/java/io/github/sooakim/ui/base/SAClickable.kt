package io.github.sooakim.ui.base

import io.github.sooakim.ui.model.SAPresentation
import io.reactivex.subjects.Subject

interface SAClickable<E : SAPresentation> {
    var onClick: Subject<E>
}