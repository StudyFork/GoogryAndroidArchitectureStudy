package r.test.rapp.networks

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.databinding.BindingAdapter
import r.test.rapp.main.MainContract


@BindingAdapter("onEditorAction")
fun EditText.setOnEditorAction(action: (() -> Unit)? = null) {
    setOnEditorActionListener { v, actionId, _ ->
        if (EditorInfo.IME_ACTION_SEARCH == actionId) {
            action?.invoke()
            true
        } else {
            false
        }
    }
}