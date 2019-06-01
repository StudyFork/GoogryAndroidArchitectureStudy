package ado.sabgil.studyproject.ext

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.setTextChangeListener(block: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            block.invoke(s.toString())
        }
    })
}