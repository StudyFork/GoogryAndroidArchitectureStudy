package sample.nackun.com.studyfirst.ui.ticker

import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("selectedMarketColor")
fun selectedMarketColor(textView: TextView, selectedMarket: String) {
    if (textView.text.toString() == selectedMarket) {
        textView.setTextColor(Color.BLUE)
    } else {
        textView.setTextColor(Color.GRAY)
    }
}