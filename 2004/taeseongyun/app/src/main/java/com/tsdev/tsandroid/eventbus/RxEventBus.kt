package com.tsdev.tsandroid.eventbus

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

interface RxEventBus {
    //백키 프레스 이벤트.
    val backButtonEventBehaviorSubject: BehaviorSubject<Long>

    fun sendBackButtonEvent(nextTime: Long)

    fun getBackButtonEvent(): Observable<Long>
}