package com.god.taeiim.myapplication.ui

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.god.taeiim.myapplication.extensions.fromHtml
import com.god.taeiim.myapplication.extensions.loadImage

@BindingAdapter("loadUrl")
fun ImageView.loadUrl(url: String?) {
    this.loadImage(url)
}

@BindingAdapter("setTextHtml")
fun TextView.setTextHtml(text: String?) {
    this.text = text?.fromHtml()
}

@BindingAdapter("intentLinkOnClick")
fun View.intentLinkOnClick(link: String?) {
    setOnClickListener {
        link?.let {
            ContextCompat.startActivity(
                context,
                Intent(Intent.ACTION_VIEW, Uri.parse(it)),
                null
            )
        }
    }
}