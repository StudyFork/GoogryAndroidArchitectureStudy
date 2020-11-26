package com.architecture.androidarchitecturestudy.adapter

import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.architecture.androidarchitecturestudy.data.model.Movie
import com.architecture.androidarchitecturestudy.databinding.MovieItemBinding

class MovieViewHolder(private val binding: MovieItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    lateinit var tempData: Movie

    init {
        itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(tempData.link))
            this.itemView.context.startActivity(intent)
        }
    }

    fun onBind(movieData: Movie) {
        binding.movie = movieData
    }
}

    private fun htmlVersionCheck(movieData: Movie): Spanned? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(movieData.title, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(movieData.title)
        }
    }
}