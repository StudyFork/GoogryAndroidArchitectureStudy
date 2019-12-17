package com.example.studyapplication.main.movie.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvTitle : TextView = itemView.tvTitle
    val tvDirector : TextView = itemView.tvDirector
    val tvActor : TextView = itemView.tvActor
    val ivImage : ImageView = itemView.ivImage
}