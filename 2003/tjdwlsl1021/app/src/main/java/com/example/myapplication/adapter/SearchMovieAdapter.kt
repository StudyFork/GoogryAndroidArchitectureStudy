package com.example.myapplication.adapter

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

/**
 * MutableList : 가변, 변경가능한 리스트
 * */
class SearchMovieAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val movieInfoArrayList: MutableList<SearchMovieItem> = arrayListOf()
    private lateinit var onClickListener: (SearchMovieItem) -> Unit

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
        val item = movieInfoArrayList[position]

        movieViewHolder.tv_title.text = Html.fromHtml(item.title)
        movieViewHolder.rb_userRating.rating = item.userRating.toFloat() / 2
        movieViewHolder.pubDate.text = item.pubDate
        movieViewHolder.tv_director.text = Html.fromHtml(item.director)
        movieViewHolder.tv_actor.text = Html.fromHtml(item.actor)

        Glide.with(movieViewHolder.iv_thumbnail).load(item.image)
            .into(movieViewHolder.iv_thumbnail)

        movieViewHolder.iv_thumbnail.setOnClickListener {
            onClickListener(item)
        }
    }

    fun setOnclickListener(onClickListener: (SearchMovieItem) -> Unit) {
        this.onClickListener = onClickListener
    }

    override fun getItemCount(): Int {
        return movieInfoArrayList.size
    }

    fun addItems(items: java.util.ArrayList<SearchMovieItem>) {
        movieInfoArrayList.clear()
        movieInfoArrayList.addAll(items)
        notifyDataSetChanged()
    }

    class MovieViewHolder internal constructor(view: View) :
        RecyclerView.ViewHolder(view) {

        val iv_thumbnail: ImageView = view.findViewById(R.id.iv_thumbnail)
        val tv_title: TextView = view.findViewById(R.id.tv_title)
        val rb_userRating: RatingBar = view.findViewById(R.id.rb_userRating)
        val pubDate: TextView = view.findViewById(R.id.tv_pubDate)
        val tv_director: TextView = view.findViewById(R.id.tv_director)
        val tv_actor: TextView = view.findViewById(R.id.tv_actor)

    }

}