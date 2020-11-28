package com.example.androidarchitecturestudy.util

import android.app.Activity
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.example.androidarchitecturestudy.MainActivity


@BindingAdapter("activity", requireAll = false)
fun EditText.searchMovie(activity: Activity){

    // edittext search action 눌릴때
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {

              //메인엑티비티일경우  SearchMovie 작동
             (activity as MainActivity).searchMovie(this)
              activity.hideKeyboard()
            true
        } else {
            false
        }
    }
}