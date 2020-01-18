package com.siwon.prj.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.siwon.prj.common.base.BaseRecyclerViewAdapter
import com.siwon.prj.common.model.Movie
import com.siwon.prj.databinding.ListItemBinding

class MovieAdapter(private val clickListener: ((String) -> Unit)? = null) :
    BaseRecyclerViewAdapter<Movie, ListItemBinding>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieHolder(
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        ) { position ->
            getPositionItem(position)?.also { clickListener?.invoke(it.link) }
        }
}