package r.test.rapp.ui.extend

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.databinding.BindingAdapter


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