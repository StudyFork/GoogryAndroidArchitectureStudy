package study.architecture.myarchitecture.util

import study.architecture.myarchitecture.ui.main.MainActivity

object Filter {
    const val KEY_ORDER = "order"

    const val ASC = 0 //오름차순
    const val DESC = 1 //내림차순

    const val KEY_FIELD = "field"

    const val COIN_NAME = "코인명"
    const val LAST = "현재가"
    const val TRADE_DIFF = "전일대비"
    const val TRADE_AMOUNT = "거래대금"

    fun selectArrowToFilter(selectArrow: MainActivity.SelectArrow) = when (selectArrow) {
        MainActivity.SelectArrow.COIN_NAME -> COIN_NAME
        MainActivity.SelectArrow.LAST -> LAST
        MainActivity.SelectArrow.TRADE_DIFF -> TRADE_DIFF
        MainActivity.SelectArrow.TRADE_AMOUNT -> TRADE_AMOUNT
    }
}