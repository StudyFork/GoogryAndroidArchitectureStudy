package com.siwon.prj

import android.view.LayoutInflater
import android.view.ViewGroup
import com.siwon.prj.common.adapter.BaseRecyclerViewAdapter
import com.siwon.prj.model.Movie

class MovieAdapter(private val clickListener: ((String) -> Unit)? = null) : BaseRecyclerViewAdapter<Movie>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
        ) { position ->
            getPositionItem(position)?.also {
                clickListener?.let { clickListener -> clickListener(it.link) }
            }
        }
}