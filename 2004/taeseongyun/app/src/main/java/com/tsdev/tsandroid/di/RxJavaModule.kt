package com.tsdev.tsandroid.di

import com.tsdev.tsandroid.eventbus.RxEventBus
import com.tsdev.tsandroid.eventbus.RxEventBusImpl
import com.tsdev.tsandroid.util.BackKeyPressExt
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.dsl.module

val rxJavaEventBusModule = module {
    single<RxEventBus> { RxEventBusImpl() }


    /***
     * todo single not working.
     * todo factory working.
     ***/

    factory { (finish: () -> Unit, showToast: (Int, Int) -> Unit, compositeDisposable: CompositeDisposable) ->
        BackKeyPressExt(
            get(),
            compositeDisposable,
            finish,
            showToast
        )
    }
}