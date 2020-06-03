package com.project.architecturestudy.components

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.project.architecturestudy.R
import com.squareup.picasso.Picasso
import org.apache.commons.text.StringEscapeUtils
import org.jsoup.Jsoup

fun String.parseHTMLTag(): String {
    val strWithoutSign = StringEscapeUtils.unescapeHtml4(this)
    return Jsoup.parse(strWithoutSign).text()
}

@BindingAdapter("setImgUrl")
fun ImageView.setImgUrl(imgUrl: String?) {
    Log.d("bsjbsj", "movieItem.image   imgUrl:$imgUrl")
    imgUrl?.let {
        Picasso.get()
            .load(it)
            .placeholder(R.drawable.ic_no_resource)
            .centerCrop()
            .error(R.drawable.ic_no_resource)
            .fit()
            .into(this)
    }
}

@BindingAdapter("parseHtml")
fun TextView.parseHtml(text: String?) {
    this.text = text?.parseHTMLTag()
}
    

