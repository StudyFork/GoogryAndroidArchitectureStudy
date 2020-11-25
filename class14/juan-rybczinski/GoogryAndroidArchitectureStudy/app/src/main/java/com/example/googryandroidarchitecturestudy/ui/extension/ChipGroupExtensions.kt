package com.example.googryandroidarchitecturestudy.ui.extension

import androidx.databinding.BindingAdapter
import com.example.googryandroidarchitecturestudy.domain.RecentSearch
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

@BindingAdapter("addChildren")
fun ChipGroup.addChildren(children: List<RecentSearch>) {
    children.forEach {
        addView(Chip(context).apply {
            text = it.query
        })
    }
}