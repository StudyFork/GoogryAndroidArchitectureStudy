package com.namjackson.archstudy.base.component

import android.text.Editable
import android.text.TextWatcher

interface BaseTextWatcher : TextWatcher {

    override fun afterTextChanged(s: Editable) {
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }
}