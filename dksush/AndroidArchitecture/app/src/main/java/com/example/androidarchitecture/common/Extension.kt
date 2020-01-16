package com.example.androidarchitecture.common

import android.annotation.SuppressLint
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidarchitecture.ui.base.BaseRecyclerAdapter
import com.example.androidarchitecture.ui.base.BaseViewHolder


@SuppressLint("ShowToast")
fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

}

@BindingAdapter("htmlText")
fun TextView.changeHtmlText(text: String) {
    this.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_COMPACT)
}

@BindingAdapter("bindImage")
fun bindImage(view: ImageView, res: String) {
    Glide.with(view.context)
        .load(res)
        .into(view)
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("setData")
fun RecyclerView.setData(items: List<Any>?) {
    (this.adapter as? BaseRecyclerAdapter<Any, BaseViewHolder<Any>>)?.run {
        items?.let {
            setData(items)
        }
    }
}
