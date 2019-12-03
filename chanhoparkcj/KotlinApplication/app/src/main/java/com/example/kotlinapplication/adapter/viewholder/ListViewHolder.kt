package com.example.kotlinapplication.adapter.viewholder

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinapplication.R
import com.example.kotlinapplication.adapter.ListNaverAdapter
import com.example.kotlinapplication.model.MovieItems
import com.squareup.picasso.Picasso
import java.net.URL

class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val mView: View = view
    private val imageView: ImageView = view.findViewById(R.id.movie_item_image)
    private val titleView: TextView = view.findViewById(R.id.movie_item_title)
    private val ratingView: RatingBar = view.findViewById(R.id.movie_item_user_rating)
    private val pubDateView: TextView = view.findViewById(R.id.movie_item_pubDate)
    private val directorView: TextView = view.findViewById(R.id.movie_item_director)
    private val actorView: TextView = view.findViewById(R.id.movie_item_actor)
    private var mItemLisener: ListNaverAdapter.ItemListener? = null
    private val movieLayout: LinearLayout = view.findViewById(R.id.movie_item_layout)

    fun bind(item: MovieItems, listener: ListNaverAdapter.ItemListener?) {
        mItemLisener = listener

        if (!item.image.equals("")) {
            Picasso.get()
                .load(item.image)
                .into(imageView)
        }
        ratingView.rating = item.userRating.toFloat()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            titleView.text = Html.fromHtml(item.title, 0)
            pubDateView.text = Html.fromHtml(item.pubDate, 0)
            directorView.text = Html.fromHtml(item.director, 0)
            actorView.text = Html.fromHtml(item.actor, 0)
        } else {
            titleView.text = item.title
            pubDateView.text = item.pubDate
            directorView.text = item.director
            actorView.text = item.actor
        }
        movieLayout.setOnClickListener({
            mItemLisener!!.onItemClick(item)
        })


    }
}