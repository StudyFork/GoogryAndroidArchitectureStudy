package com.example.archstudy.ui.main

import android.os.Build
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.archstudy.data.source.local.MovieData
import com.example.archstudy.R
import com.example.archstudy.databinding.ItemMovieBinding

class MovieListAdapter(@Nullable private var listener: ItemClickListener) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private var list = mutableListOf<MovieData>()
    private lateinit var binding: ItemMovieBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val holder = ViewHolder(binding)

        holder.itemView.setOnClickListener {
            listener.onItemClick(list[holder.adapterPosition].link)
        }
        return holder
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[position])

    fun setAllData(movieList: MutableList<MovieData>) {
        list.clear()
        list.addAll(movieList)
        notifyDataSetChanged()
    }

    class ViewHolder(private var binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

//        private val ivThumbnail = itemView.findViewById<ImageView>(R.id.ivThumbNail)
//        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
//        private val ratingMovie = itemView.findViewById<RatingBar>(R.id.ratingMovie)
//        private val tvReleaseYear = itemView.findViewById<TextView>(R.id.tvReleaseYear)
//        private val tvDirector = itemView.findViewById<TextView>(R.id.tvDirector)
//        private val tvActors = itemView.findViewById<TextView>(R.id.tvActors)

        fun bind(data: MovieData) {

            with(data) {
                Log.d("img", "image : $image, title : $title")
                val convertedText = htmlToString(title)
                with(binding){
                    tvTitle.text = convertedText
                    ratingMovie.rating = userRating.toFloat() / 2
                    tvReleaseYear.text = pubDate
                    tvDirector.text = director
                    tvActors.text = actor
                    loadImage(this,image)
                }
            }

        }

        private fun loadImage(binding: ItemMovieBinding ,image: String) {

            with(binding){
                if (image.trim().isNotEmpty()) {
                    Glide.with(itemView.context)
                        .load(image)
                        .override(600, 1000)
                        .into(ivThumbNail)
                } else {
                    Glide.with(itemView.context)
                        .load(R.drawable.no_image)
                        .override(600, 1000)
                        .into(ivThumbNail)
                }
            }
        }

        private fun htmlToString(htmlText: String): String {
            // Android N Version 이상의 경우
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY).toString()
            } else {
                // 그 외
                Html.fromHtml(htmlText).toString()
            }
        }
    }

    interface ItemClickListener {
        fun onItemClick(url: String)
    }

}