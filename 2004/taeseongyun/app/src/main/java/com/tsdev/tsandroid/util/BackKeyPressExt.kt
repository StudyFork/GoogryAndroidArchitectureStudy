package com.tsdev.tsandroid.util

import android.widget.Toast
import com.tsdev.tsandroid.R
import com.tsdev.tsandroid.constant.Const
import com.tsdev.tsandroid.eventbus.RxEventBus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class BackKeyPressExt(
    private val rxEventBus: RxEventBus,
    compositeDisposable: CompositeDisposable,
    finish: () -> Unit,
    showToast: (Int, Int) -> Unit
) {
    init {
        compositeDisposable.add(
            rxEventBus.getBackButtonEvent()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .buffer(2, 1)
                .map { it[0] to it[1] }
                .subscribe({
                    if (it.second - it.first > Const.BACK_BUTTON_THROTTLE_TIME)
                        showToast(R.string.destroy_view_toast_message, Toast.LENGTH_LONG)
                    else
                        finish()
                }, {
                    it.printStackTrace()
                })
        )
    }

    fun onPressedBackKey() {
        rxEventBus.sendBackButtonEvent(System.currentTimeMillis())
    }
}