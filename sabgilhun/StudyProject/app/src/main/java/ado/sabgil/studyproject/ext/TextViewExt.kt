package ado.sabgil.studyproject.ext

import ado.sabgil.studyproject.R
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["setOrderIndicator", "setIndicatorVisibility"], requireAll = false)
fun TextView.setOrderIndicator(isAscending: Boolean, isVisibility: Boolean) {
    if (!isVisibility) {
        setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        return
    }

    if (isAscending) {
        setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.vector_arrow_drop_up, 0)
    } else {
        setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.vector_arrow_drop_down, 0)
    }
}