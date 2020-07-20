package m.woong.architecturestudy.util

import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import m.woong.architecturestudy.R


@BindingAdapter("glideImage")
fun ImageView.setGlideImage(url: String) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(this)
}

@BindingAdapter("htmlText")
fun TextView.setParsedHtmlText(text: String) {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        this.text = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY).toString()
    } else {
        this.text = Html.fromHtml(text).toString()
    }
}
