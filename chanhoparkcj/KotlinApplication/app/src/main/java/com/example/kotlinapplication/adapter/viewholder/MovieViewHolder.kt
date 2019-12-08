package com.example.kotlinapplication.adapter.viewholder

import android.os.Build
import android.text.Html
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.adapter.ListNaverAdapter
import com.example.kotlinapplication.model.MovieItems
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_list_item.view.*

class ListViewHolder(view: View,type:String) : RecyclerView.ViewHolder(view) {
    private val mView: View = view
    fun bind(item: MovieItems, listener: ListNaverAdapter.ItemListener?) {

        if (!item.image.equals("")) {
            Picasso.get()
                .load(item.image)
                .resize(300, 450)
                .into(mView.movie_item_image)
        }
        mView.movie_item_user_rating.rating = item.userRating.toFloat()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mView.movie_item_title.text = Html.fromHtml(item.title, 0)
            mView.movie_item_pubDate.text = Html.fromHtml(item.pubDate, 0)
            mView.movie_item_director.text = Html.fromHtml(item.director, 0)
            mView.movie_item_actor.text = Html.fromHtml(item.actor, 0)
        } else {
            mView.movie_item_title.text = item.title
            mView.movie_item_pubDate.text = item.pubDate
            mView.movie_item_director.text = item.director
            mView.movie_item_actor.text = item.actor
        }
        mView.movie_item_layout.setOnClickListener {
            listener!!.onItemClick(item)
        }


    }
}