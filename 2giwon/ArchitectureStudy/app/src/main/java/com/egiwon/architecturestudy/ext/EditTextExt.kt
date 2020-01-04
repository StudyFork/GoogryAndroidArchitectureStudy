package com.egiwon.architecturestudy.ext

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener

@BindingAdapter("query")
fun EditText.setQuery(query: String?) {
    if (text.toString() == query)
        return

    setText(query)
}

@InverseBindingAdapter(attribute = "query", event = "queryTextChanged")
fun EditText.getQuery(): String = text.toString()


@BindingAdapter("queryTextChanged")
fun EditText.addQueryTextChanged(listener: InverseBindingListener) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            listener.onChange()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

    })
}