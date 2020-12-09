package com.showmiso.architecturestudy.extensions

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("setUrl")
fun ImageView.setUrl(url: String) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}

@BindingAdapter("titleString")
fun getTitleString(tv: TextView, title: String?) {
    tv.text = title?.replace("<b>", "")?.replace("</b>", "")
}

@BindingAdapter("directorString")
fun getDirectorString(tv: TextView, director: String?) {
    tv.text = director?.let {
        if (it.isEmpty()) {
            ""
        } else {
            it.substring(0, director.length - 1).replace("|", ", ")
        }
    }
}

@BindingAdapter("onEditorEnter")
fun EditText.onEditorEnter(callback: () -> Unit) {
    setOnEditorActionListener { textView, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            callback()
            return@setOnEditorActionListener true
        }
        return@setOnEditorActionListener false
    }
}