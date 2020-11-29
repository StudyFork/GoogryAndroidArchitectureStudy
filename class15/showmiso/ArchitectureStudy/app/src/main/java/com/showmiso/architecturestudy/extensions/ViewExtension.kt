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
            // TODO: 여기서 textView.toString() 을 callback 에 넘기려면 어떻게 해야하나요?
            // xml 에서 자꾸 오류가 나서요 ㅠ
            callback()
            return@setOnEditorActionListener true
        }
        return@setOnEditorActionListener false
    }
}