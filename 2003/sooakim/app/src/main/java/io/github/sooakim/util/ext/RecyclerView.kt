package io.github.sooakim.util.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.sooakim.ui.base.SAIdentifiable
import io.github.sooakim.ui.base.SARecyclerViewAdapter
import io.github.sooakim.ui.model.SAPresentation

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun <E> RecyclerView.bindList(list: List<E>) where E : SAIdentifiable, E : SAPresentation {
    (this.adapter as? SARecyclerViewAdapter<E>)?.submitList(list)
}