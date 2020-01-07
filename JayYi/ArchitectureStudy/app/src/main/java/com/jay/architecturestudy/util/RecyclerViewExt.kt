package com.jay.architecturestudy.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.jay.architecturestudy.widget.SpacesItemDecoration

@BindingAdapter("app:divider_item_decoration")
fun RecyclerView.addDividerItemDecoration(orientation: Int) {
    addItemDecoration(
        DividerItemDecoration(
            context, orientation
        )
    )
}

@BindingAdapter(
    "app:space_item_item_decoration_bottom",
    "app:space_item_item_decoration_spacing",
    "app:space_item_item_decoration_first_top",
    requireAll = true
)
fun RecyclerView.addSpacesItemDecoration(bottom: Int, spacing: Int, firstTop: Int) {
    addItemDecoration(
        SpacesItemDecoration(
            bottom.toPx(),
            spacing.toPx(),
            firstTop.toPx()
        )
    )

}