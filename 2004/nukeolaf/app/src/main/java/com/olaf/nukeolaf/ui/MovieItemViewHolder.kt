package com.olaf.nukeolaf.ui

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.Html
import androidx.recyclerview.widget.RecyclerView
import com.olaf.nukeolaf.data.model.MovieItem
import com.olaf.nukeolaf.databinding.ItemMoviesRvBinding

class MovieItemViewHolder(private val binding: ItemMoviesRvBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private lateinit var movieItem: MovieItem

    init {
        itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movieItem.link))
            it.context.startActivity(intent)
        }
    }

    fun bind(item: MovieItem) {
        movieItem = item
        with(binding) {
            movieItem = item
            executePendingBindings()
        }
    }

    private fun String.htmlToString(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            Html.fromHtml(this).toString()
        }
    }

    private fun String.addCommas(prefix: String): String {
        return if (this.isNotEmpty()) {
            this.substring(0, this.length - 1)
                .split("|")
                .joinToString(
                    prefix = prefix,
                    separator = ", "
                )
        } else {
            this
        }

    }

}