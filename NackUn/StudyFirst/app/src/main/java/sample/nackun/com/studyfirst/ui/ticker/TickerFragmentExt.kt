package sample.nackun.com.studyfirst.ui.ticker

import android.databinding.BindingAdapter
import android.graphics.Color
import android.widget.TextView

@BindingAdapter("app:selectedMarketColor")
fun selectedMarketColor(textView: TextView, selectedMarket: String) {
    if (textView.text.toString().equals(selectedMarket)) {
        textView.setTextColor(Color.BLUE)
    } else {
        textView.setTextColor(Color.GRAY)
    }
}