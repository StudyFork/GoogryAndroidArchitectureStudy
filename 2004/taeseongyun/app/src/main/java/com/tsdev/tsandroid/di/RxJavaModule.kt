package com.tsdev.tsandroid.di

import com.tsdev.tsandroid.eventbus.RxEventBus
import com.tsdev.tsandroid.eventbus.RxEventBusImpl
import com.tsdev.tsandroid.util.BackKeyPressExt
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.dsl.module

val rxJavaEventBusModule = module {
    single<RxEventBus> { RxEventBusImpl() }


    /***todo single로 처리 할 경우 한번만 실행 되는데 이유를 모르겠음.
     * factory로 할 경우는 정상 작동.
     ***/

    factory { (finish: () -> Unit, showToast: (Int, Int) -> Unit) ->
        BackKeyPressExt(
            get(),
            get(),
            finish,
            showToast
        )
    }

    single { CompositeDisposable() }
}