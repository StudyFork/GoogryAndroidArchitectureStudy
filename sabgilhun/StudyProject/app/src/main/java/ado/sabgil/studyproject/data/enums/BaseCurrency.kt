package ado.sabgil.studyproject.data.enums

import java.text.DecimalFormat

enum class BaseCurrency(
    val base: String,
    val decimalPattern: DecimalFormat,
    val bigDecimalPattern: DecimalFormat
) {
    KRW(
        base = "KRW",
        decimalPattern = DecimalFormat("#,###.##"),
        bigDecimalPattern = DecimalFormat("#,###")
    ),

    BTC(
        base = "BTC",
        decimalPattern = DecimalFormat("0.00000000"),
        bigDecimalPattern = DecimalFormat("0.00000000")
    ),

    ETH(
        base = "ETH",
        decimalPattern = DecimalFormat("0.00000000"),
        bigDecimalPattern = DecimalFormat("0.00000000")
    ),

    USDT(
        base = "USDT",
        decimalPattern = DecimalFormat("#,##0.000"),
        bigDecimalPattern = DecimalFormat("#,##0.000")
    ),

    DEFAULT(
        base = "DEFAULT",
        decimalPattern = DecimalFormat("#,##0.00000000"),
        bigDecimalPattern = DecimalFormat("#,##0.00000000")
    )

}