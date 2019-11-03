package com.android.studyfork.network.model

import androidx.annotation.ColorRes
import com.android.studyfork.util.*


/**
 * created by onemask
 */
data class Ticker(
    val marketName : String, //코인명
    val currentTradePrice : Double, //현재가
    val beforeSignedChangeRate : Double, //전일대비
    @ColorRes val diffTextColorId: Int, //색
    val totalTrade : Double  //거래대금
){
    fun getcurrentTradePrice() : String{
        return currentTradePrice.toString().filterTrade(currentTradePrice)
    }

    fun getmarketName() : String{
        return marketName.substringAfter("-")
    }

    fun getbeforeSignedChangeRate() : String{
        return beforeSignedChangeRate.toString().setTradeDiff(beforeSignedChangeRate)
    }

    fun getbeforeColorStatus() : Int{
        return beforeSignedChangeRate.toString().setTextColor(beforeSignedChangeRate)
    }

    fun getTotalTrade() : String{
        return DataFormatUtills.setTradeVolumeText(marketName.substringBefore("-"),totalTrade)
    }

}



interface TickerImpl{
    fun toTicker() : Ticker
}