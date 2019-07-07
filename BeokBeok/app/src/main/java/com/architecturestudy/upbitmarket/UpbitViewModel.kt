package com.architecturestudy.upbitmarket

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import com.architecturestudy.base.BaseViewModel
import com.architecturestudy.data.upbit.UpbitRepository
import com.architecturestudy.data.upbit.UpbitTicker
import com.architecturestudy.util.NumberFormatter

class UpbitViewModel(
    private val upBitRepository: UpbitRepository
) : BaseViewModel() {

    val marketPriceList = ObservableField<List<Map<String, String>>>()
    val errMsg = ObservableField<Throwable>()
    val isMarketTypeClicked = ObservableField<Boolean>()

    init {
        registerPropertyChangedCallbacks()
        upBitRepository.getMarketPrice("KRW")
    }

    private fun registerPropertyChangedCallbacks() {
        @Suppress("UNCHECKED_CAST")
        upBitRepository.marketPriceList.addOnPropertyChangedCallback(
            object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    if (sender is ObservableField<*>) {
                        marketPriceList.set(
                            NumberFormatter.convertTo(sender.get() as List<UpbitTicker>)
                        )
                    }
                }
            }
        )
        upBitRepository.throwable.addOnPropertyChangedCallback(
            object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    if (sender is ObservableField<*>) {
                        errMsg.set(sender.get() as Throwable)
                    }
                }
            }
        )
    }

    fun showMarketPrice(v: View) {
        isMarketTypeClicked.set(false)
        if (v is TextView) {
            upBitRepository.getMarketPrice(v.text.toString())
            v.setTextColor(Color.BLUE)
            isMarketTypeClicked.set(true)
        }
    }
}