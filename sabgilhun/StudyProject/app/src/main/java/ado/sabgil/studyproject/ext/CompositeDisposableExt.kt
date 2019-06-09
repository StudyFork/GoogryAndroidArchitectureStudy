package ado.sabgil.studyproject.ext

import io.reactivex.disposables.CompositeDisposable

fun CompositeDisposable.isEmpty(): Boolean {
    return this.size() == 0
}

fun CompositeDisposable.isNotEmpty() = !this.isEmpty()