package com.example.studyapplication.main.image.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_image.view.*

class ImageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvTitle : TextView = itemView.tvTitle
    val ivImage : ImageView = itemView.ivImage
}