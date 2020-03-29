package io.github.sooakim.util.ext

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("error")
fun TextInputLayout.bindError(error: String?) {
    this.error = error
}