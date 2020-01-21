package com.jay.architecturestudy.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.jay.architecturestudy.R
import com.jay.architecturestudy.databinding.ViewSearchBinding
import com.jay.architecturestudy.ui.BaseViewModel
import com.jay.architecturestudy.util.hideKeyboard

class NaverSearchBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val binding: ViewSearchBinding = DataBindingUtil.inflate<ViewSearchBinding>(
        LayoutInflater.from(context),
        R.layout.view_search,
        this,
        true
    )

    var onClickAction: ((String) -> Unit)? = null

    var keyword: String? = null
        set(value) {
            if (field != value) {
                field = value
            }
        }

    init {
    }

    fun setViewModel(viewModel: BaseViewModel<*>) {

    }
}



