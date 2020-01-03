package com.example.androidstudy.adapter

import android.widget.TextView
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods

@BindingMethods(
    BindingMethod(
        type = TextView::class,
        attribute = "android:onEditorAction",
        method = "setOnEditorActionListener"
    )
)
class MyBindingMethods