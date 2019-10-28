package com.ironelder.androidarchitecture.view

import android.content.Context
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.ironelder.androidarchitecture.R

class CustomItemView:ConstraintLayout {
    constructor(context: Context?):super(context)
    init {
        LayoutInflater.from(context).inflate(R.layout.item_custom_item_view, this, true)
    }
}