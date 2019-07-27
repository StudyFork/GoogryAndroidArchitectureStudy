package com.architecturestudy.util

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object RxEventBus {
    private val subject: PublishSubject<Any> by lazy {
        PublishSubject.create<Any>()
    }

    fun sendEvent(any: Any) = subject.onNext(any)

    fun getEvents(): Observable<Any> = subject
}