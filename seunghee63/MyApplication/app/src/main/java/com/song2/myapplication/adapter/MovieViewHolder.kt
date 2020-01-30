package com.song2.myapplication.adapter

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.song2.myapplication.databinding.RvMovieItemBinding
import com.song2.myapplication.source.MovieData

@Suppress("DEPRECATION")
class MovieViewHolder(private val binding: RvMovieItemBinding) :
    RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    override fun onClick(view: View?) {
        val pos = adapterPosition

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(binding.movie!!.link))
        this.itemView.context.startActivity(intent)
    }

    fun onBind(movieData: MovieData) {
        binding.movie = movieData

        itemView.setOnClickListener {
            onClick(itemView)
        }
    }
}