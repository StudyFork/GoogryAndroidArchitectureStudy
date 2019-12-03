package com.jay.architecturestudy.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.jay.architecturestudy.R
import com.jay.architecturestudy.util.hideKeyboard
import kotlinx.android.synthetic.main.view_search.*
import kotlinx.android.synthetic.main.view_search.view.*

class NaverSearchBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val debounceTime: Long = 600L

    private var lastClickTime: Long = 0

    var onClickAction: ((String) -> Unit)? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.view_search, this, true)

        search_btn.setOnClickListener {
            if (System.currentTimeMillis() - lastClickTime < debounceTime) {
                return@setOnClickListener
            }
            val keyword = search_editor.text.toString().trim()
            if (keyword.isBlank()) {
                Toast.makeText(context, context.getString(R.string.warn_input_keyword), Toast.LENGTH_SHORT)
                    .show()
            } else {
                hideKeyboard()
                onClickAction?.invoke(keyword)
            }

        }
    }


}