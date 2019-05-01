package dev.daeyeon.gaasproject.data

import com.google.gson.annotations.SerializedName
import dev.daeyeon.gaasproject.data.source.Ticker

data class TickerResponse(
        /**
         * 종목 구분 코드
         */
        @SerializedName("market") val market: String,
        /**
         * 최근 거래 일자(UTC)
         */
        @SerializedName("trade_date") val tradeDate: String,
        /**
         * 최근 거래 시각(UTC)
         */
        @SerializedName("trade_time") val tradeTime: String,
        /**
         * 최근 거래 일자(KST)
         */
        @SerializedName("trade_date_kst") val tradeDateKst: String,
        /**
         * 최근 거래 시각(KST)
         */
        @SerializedName("trade_time_kst") val tradeTimeKst: String,
        /**
         * 시가
         */
        @SerializedName("opening_price") val openingPrice: Double,
        /**
         * 고가
         */
        @SerializedName("high_price") val highPrice: Double,
        /**
         * 저가
         */
        @SerializedName("low_price") val lowPrice: Double,
        /**
         * 종가
         */
        @SerializedName("trade_price") val tradePrice: Double,
        /**
         * 전일 종가
         */
        @SerializedName("prev_closing_price") val prevClosingPrice: Double,
        /**
         *EVEN : 보험, RISE : 상승, FALL : 하락
         */
        @SerializedName("change") val change: String,
        /**
         * 변화액의 절대값
         */
        @SerializedName("change_price") val changePrice: Double,
        /**
         * 변화율의 절대값
         */
        @SerializedName("change_rate") val changeRate: Double,
        /**
         * 부호가 있는 변화액
         */
        @SerializedName("signed_change_price") val signedChangePrice: Double,
        /**
         * 부호가 있는 변화율
         */
        @SerializedName("signed_change_rate") val signedChangeRate: Double,
        /**
         * 가장 최근 거래량
         */
        @SerializedName("trade_volume") val tradeVolume: Double,
        /**
         * 누적 거래대금(UTC 0시 기준)
         */
        @SerializedName("acc_trade_price") val accTradePrice: Double,
        /**
         * 24시간 누적 거래대금
         */
        @SerializedName("acc_trade_price_24h") val accTradePrice24h: Double,
        /**
         * 누적 거래량(UTC 0시 기준)
         */
        @SerializedName("acc_trade_volume") val accTradeVolume: Double,
        /**
         * 24시간 누적 거래대금
         */
        @SerializedName("acc_trade_volume_24h") val accTradeVolume24h: Double,
        /**
         * 52주 신고가
         */
        @SerializedName("highest_52_week_price") val highest52WeekPrice: Double,
        /**
         * 52주 신고가 달성일
         */
        @SerializedName("highest_52_week_date") val highest52WeekDate: String,
        /**
         * 52주 신저가
         */
        @SerializedName("lowest_52_week_price") val lowest52WeekPrice: Double,
        /**
         * 52주 신저가 달성일
         */
        @SerializedName("lowest_52_week_date") val lowest52WeekDate: String,
        /**
         * 타임스탬프
         */
        @SerializedName("timestamp") val timestamp: Long
) {
    fun toTicker() = Ticker(
            market = this.market,
            tradePrice = this.tradePrice.toString(),
            signedChangeRate = "${String.format("%4.2f", this.signedChangeRate * 100)}%",
            accTradePrice24h = String.format("%.2f", this.accTradePrice24h)
    )
}