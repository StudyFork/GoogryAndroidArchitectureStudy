package com.jay.aas.binding

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.databinding.BindingAdapter

@BindingAdapter("onEditorEnterAction")
fun EditText.onEditorEnterAction(action: (() -> Unit)? = null) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            action?.invoke()
            return@setOnEditorActionListener true
        }
        return@setOnEditorActionListener false
    }
}