package com.example.kotlinapplication.adapter.viewholder

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.adapter.ListAdapter
import com.example.kotlinapplication.model.MovieItems
import kotlinx.android.synthetic.main.movie_list_item.view.*

class ListViewHolder(view: View):RecyclerView.ViewHolder(view) {
    val imageView:ImageView = view.findViewById(R.id.movie_item_image)
    val titleView:TextView=view.findViewById(R.id.movie_item_title)
    val ratingView:RatingBar=view.findViewById(R.id.movie_item_user_rating)
    val pubDateView:TextView=view.findViewById(R.id.movie_item_pubDate)
    val directorView:TextView=view.findViewById(R.id.movie_item_director)
    val actorView:TextView=view.findViewById(R.id.movie_item_actor)

    fun bind(item:MovieItems,listener:ListAdapter.ItemListener,position:Int){
        imageView!!.setImageURI(Uri.parse(item!!.image))
        titleView!!.setText(item!!.title)
        pubDateView!!.setText(item!!.pubDate)
        directorView!!.setText(item!!.director)
        actorView!!.setText(item!!.actor)
    }
}