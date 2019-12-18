package com.example.studyapplication.main.blog.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_blog.view.*

class BlogHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvTitle: TextView = itemView.tvTitle
    val tvDescription: TextView = itemView.tvDescription
    val tvBloggername: TextView = itemView.tvBloggername
}