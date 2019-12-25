package com.egiwon.architecturestudy.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("setHasFixedSize")
fun RecyclerView.bindHasFixedSize(fixedSize: Boolean) = setHasFixedSize(fixedSize)
