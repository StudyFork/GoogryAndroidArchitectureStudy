package com.project.architecturestudy.components

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.project.architecturestudy.components.Constants.NO_IMAGE_URL
import com.squareup.picasso.Picasso
import org.apache.commons.text.StringEscapeUtils
import org.jsoup.Jsoup

fun String.parseHTMLTag(): String {
    val strWithoutSign = StringEscapeUtils.unescapeHtml4(this)
    return Jsoup.parse(strWithoutSign).text()
}

@BindingAdapter("setImgUrl", "setAlternativeImg", requireAll = true)
fun ImageView.setImgUrl(imgUrl: String?, alternativeImg: Drawable) {
    imgUrl?.let { nImgUrl ->
        Picasso.get()
            .load(nImgUrl.ifEmpty {
                NO_IMAGE_URL
            })
            .placeholder(alternativeImg)
            .centerCrop()
            .error(alternativeImg)
            .fit()
            .into(this)
    }
}

@BindingAdapter("parseHtml")
fun TextView.parseHtml(text: String?) {
    this.text = text?.parseHTMLTag()
}
    

