package com.example.androidarchitecturestudy.util

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.databinding.BindingAdapter


@BindingAdapter("searchMovie")
fun EditText.searchMovie(searchMovie: () -> Unit) {

    // edittext search action 눌릴때
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            searchMovie.invoke()
            true
        } else {
            false
        }
    }
}