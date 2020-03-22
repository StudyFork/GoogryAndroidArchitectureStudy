package com.example.myapplication.ui

import android.annotation.TargetApi
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.data.local.MovieEntity
import com.example.myapplication.databinding.ItemMovieBinding

class SearchMovieAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val movieInfoArrayList: MutableList<MovieEntity> = arrayListOf()
    private lateinit var onClickListener: (MovieEntity) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater)
        val viewHolder = MovieViewHolder(binding)

        //-onClick은 onCrateViewHoler에서 선언, *bindViewHolder에서 선언하면 bind할 때마다 생성되는 문제
        binding.root.setOnClickListener {
            onClickListener(movieInfoArrayList[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    @TargetApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movieViewHolder = holder as MovieViewHolder
        val item = movieInfoArrayList[position]

        movieViewHolder.tvTitle.text = Html.fromHtml(item.title, Html.FROM_HTML_MODE_COMPACT)
        movieViewHolder.rbUserRating.rating = item.userRating.toFloat() / 2
        movieViewHolder.pubDate.text = item.pubDate
        movieViewHolder.tvDirector.text = Html.fromHtml(item.director, Html.FROM_HTML_MODE_COMPACT)
        movieViewHolder.tvActor.text = Html.fromHtml(item.actor, Html.FROM_HTML_MODE_COMPACT)

        Glide.with(movieViewHolder.ivThumbnail)
            .load(item.image)
            .into(movieViewHolder.ivThumbnail)
    }

    fun setOnclickListener(onClickListener: (MovieEntity) -> Unit) {
        this.onClickListener = onClickListener
    }

    override fun getItemCount(): Int {
        return movieInfoArrayList.size
    }

    fun addItems(items: List<MovieEntity>) {
        movieInfoArrayList.clear()
        movieInfoArrayList.addAll(items)

        notifyDataSetChanged()
    }

    class MovieViewHolder(binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivThumbnail: ImageView = binding.ivThumbnail
        val tvTitle: TextView = binding.tvTitle
        val rbUserRating: RatingBar = binding.rbUserRating
        val pubDate: TextView = binding.tvPubDate
        val tvDirector: TextView = binding.tvDirector
        val tvActor: TextView = binding.tvActor
    }
}