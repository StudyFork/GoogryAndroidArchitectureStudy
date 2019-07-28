package study.architecture.myarchitecture.rxobserver

import android.os.Bundle
import io.reactivex.subjects.PublishSubject

object RxObserverHelper {

    val tickerListSubject = PublishSubject.create<Bundle>()

    fun notifyTickerList(bundle: Bundle) {
        tickerListSubject.onNext(bundle)
    }

}