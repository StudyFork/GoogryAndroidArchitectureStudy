package com.example.kotlinapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.adapter.viewholder.BlogViewHolder
import com.example.kotlinapplication.model.BlogItems

class ListBlogAdapter(
    listener: ItemListener
) :
    RecyclerView.Adapter<BlogViewHolder>() {

    private lateinit var items: List<BlogItems>

    private var mListener: ItemListener = listener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        return BlogViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.blog_list_item,
                parent,
                false
            )
        )


    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {

        holder.bind(items.get(position), mListener)

    }

    fun addAllItems(blogList: List<BlogItems>) {
        items = blogList
    }

    interface ItemListener {
        fun onBlogItemClick(blogItems: BlogItems)
    }


}