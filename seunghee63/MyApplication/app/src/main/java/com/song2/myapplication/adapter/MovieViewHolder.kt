package com.song2.myapplication.adapter

import android.content.Intent
import android.net.Uri
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.song2.myapplication.databinding.RvMovieItemBinding
import com.song2.myapplication.source.MovieData

@Suppress("DEPRECATION")
class MovieViewHolder(private val binding: RvMovieItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    lateinit var tempData: MovieData

    private val image: ImageView = binding.ivRvMovieItemImage
    private val title: TextView = binding.tvRvMovieItemTitle
    private val rating: RatingBar = binding.rbRvMovieItemRating
    private val director: TextView = binding.tvRvMovieItemDirector
    private val pubDate: TextView = binding.tvRvMovieItemPubDate
    private val actor: TextView = binding.tvRvMovieItemActor


    override fun onClick(view: View?) {
        val pos = adapterPosition
        Log.e("position", pos.toString())

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(tempData.link))
        this.itemView.context.startActivity(intent)
    }

    //바인딩 될 때마다 호출 .. 리스너가 반복호출 될 필요는 없음
    fun onBind(movieData: MovieData) {
        tempData = movieData

        title.text = Html.fromHtml(movieData.title)
        pubDate.text = movieData.pubDate
        actor.text = movieData.actor
        director.text = movieData.director
        rating.rating = (movieData.userRating!! / 2).toFloat()

        Glide.with(itemView.context).load(movieData.image).into(image)

        itemView.setOnClickListener {
            onClick(itemView)
        }
    }
}