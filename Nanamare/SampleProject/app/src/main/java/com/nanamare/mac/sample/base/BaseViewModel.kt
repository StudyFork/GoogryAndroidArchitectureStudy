package com.nanamare.mac.sample.base

import io.reactivex.subjects.PublishSubject

abstract class BaseViewModel {

    var isLoadingObservable: PublishSubject<Boolean> = PublishSubject.create()

    abstract fun close()

}