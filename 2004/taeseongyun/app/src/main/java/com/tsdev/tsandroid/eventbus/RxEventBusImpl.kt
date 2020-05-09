package com.tsdev.tsandroid.eventbus

import io.reactivex.rxjava3.subjects.BehaviorSubject

class RxEventBusImpl : RxEventBus {
    override val backButtonEventBehaviorSubject: BehaviorSubject<Long> =
        BehaviorSubject.createDefault(0L)

    override fun sendBackButtonEvent(nextTime: Long) {
        backButtonEventBehaviorSubject.onNext(nextTime)
    }

    override fun getBackButtonEvent() = backButtonEventBehaviorSubject
}