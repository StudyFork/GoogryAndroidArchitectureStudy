package com.example.myapplication.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.item.SearchMovieItem


class SearchMovieAdapter(
    private val context: Context,
    private val movieInfoArrayList: ArrayList<SearchMovieItem>?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val movieViewHolder = holder as MovieViewHolder
        val item = movieInfoArrayList?.get(position)

        movieViewHolder.tv_title.text = Html.fromHtml(item?.title)
        movieViewHolder.rb_userRating.rating = item?.userRating!!.toFloat() / 2
        movieViewHolder.pubDate.text = item.pubDate
        movieViewHolder.tv_director.text = Html.fromHtml(item.director)
        movieViewHolder.tv_actor.text = Html.fromHtml(item.actor)

        Glide.with(movieViewHolder.iv_thumbnail).load(item.image)
            .into(movieViewHolder.iv_thumbnail)

        movieViewHolder.iv_thumbnail.setOnClickListener {
            val link = this.movieInfoArrayList?.get(position)?.link
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movieInfoArrayList?.size!!
    }

    fun addItems(items: java.util.ArrayList<SearchMovieItem>) {
        movieInfoArrayList?.clear()
        movieInfoArrayList?.addAll(items)
        notifyDataSetChanged()
    }

    class MovieViewHolder internal constructor(view: View) :
        RecyclerView.ViewHolder(view) {

        val iv_thumbnail: ImageView
        val tv_title: TextView
        val rb_userRating: RatingBar
        val pubDate: TextView
        val tv_director: TextView
        val tv_actor: TextView

        init {
            iv_thumbnail = view.findViewById(R.id.iv_thumbnail)
            tv_title = view.findViewById(R.id.tv_title)
            rb_userRating = view.findViewById(R.id.rb_userRating)
            pubDate = view.findViewById(R.id.tv_pubDate)
            tv_director = view.findViewById(R.id.tv_director)
            tv_actor = view.findViewById(R.id.tv_actor)
        }
    }

}