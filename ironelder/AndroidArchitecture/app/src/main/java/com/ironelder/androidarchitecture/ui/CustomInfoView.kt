package com.ironelder.androidarchitecture.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.ironelder.androidarchitecture.R
import kotlinx.android.synthetic.main.layout_custom_info_view.view.*

class CustomInfoView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.layout_custom_info_view, this, true)
        setReset()
    }
    private fun setReset(){
        pb_loading.visibility = View.GONE
        tv_noSearchData.visibility = View.GONE
        this.visibility = View.GONE
    }

    fun noSearchDate(){
        this.visibility = View.VISIBLE
        pb_loading.visibility = View.GONE
        tv_noSearchData.visibility = View.VISIBLE
    }

    fun startLoading(){
        this.visibility = View.VISIBLE
        pb_loading.visibility = View.VISIBLE
        tv_noSearchData.visibility = View.GONE
    }

    fun stopLoading(){
        pb_loading.visibility = View.GONE
        tv_noSearchData.visibility = View.GONE
        this.visibility = View.GONE
    }
}