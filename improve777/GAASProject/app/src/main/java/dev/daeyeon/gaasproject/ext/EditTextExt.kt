package dev.daeyeon.gaasproject.ext

import android.databinding.BindingAdapter
import android.view.inputmethod.EditorInfo
import android.widget.EditText

@BindingAdapter("onEditorAction")
fun EditText.onEditorAction(action: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        // 키보드의 검색 키
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            action()
            return@setOnEditorActionListener true
        }
        return@setOnEditorActionListener false
    }
}