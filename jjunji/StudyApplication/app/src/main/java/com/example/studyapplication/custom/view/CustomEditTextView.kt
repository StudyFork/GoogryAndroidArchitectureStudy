package com.example.studyapplication.custom.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.example.studyapplication.R

class CustomEditTextView : EditText, View.OnFocusChangeListener {
    private var clearDrawable : Drawable

    constructor(context: Context) : super(context)
    constructor(context : Context, attrs : AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr : Int) : super(context, attrs, defStyleAttr)

    init {
        this.setBackgroundResource(R.drawable.border)
        this.addTextChangedListener(textWatcher())
//        this.setOnTouchListener(touchListener())
        this.onFocusChangeListener = this

        val tempDrawable : Drawable = ContextCompat.getDrawable(context, R.drawable.ic_clear)!!
        clearDrawable = DrawableCompat.wrap(tempDrawable)
        DrawableCompat.setTintList(clearDrawable, hintTextColors)
        clearDrawable.setBounds(0, 0, clearDrawable.intrinsicWidth, clearDrawable.intrinsicHeight)
    }

    private fun touchListener() : OnTouchListener {
        return OnTouchListener { _, event ->
            val x = event.x

            if (clearDrawable.isVisible && x > width - paddingRight - clearDrawable.intrinsicWidth) {
                if (event.action == MotionEvent.ACTION_UP) {
                    error = null
                    text = null
                }
            }
            true
        }
    }

    private fun textWatcher() : TextWatcher {
        return object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(isFocused) {
                    Log.e("CustomEditTextView", ">>> CharSequence : $s / count : $count / start : $start / before : $before")
                    if (s != null) {
                        setClearIconVisible(s.isNotEmpty())
                    }
                }
            }
        }
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if(hasFocus) {
            setClearIconVisible(text.isNotEmpty())
        } else {
            setClearIconVisible(false)
        }
    }

    private fun setClearIconVisible(visible : Boolean) {
        clearDrawable.setVisible(visible, false)
        setCompoundDrawables(null, null, if(visible) clearDrawable else null , null)
    }
}