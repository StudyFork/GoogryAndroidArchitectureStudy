package com.studyfork.architecturestudy.ui.adapter

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.studyfork.architecturestudy.data.model.MovieResponse
import com.studyfork.architecturestudy.databinding.ItemMovieBinding

class MovieResultRVAdapter(val itemClick: (movieLink: String) -> Unit) :
    RecyclerView.Adapter<MovieResultRVAdapter.MovieResultVH>() {

    private val items = mutableListOf<MovieResponse.Item>()

    fun setItems(items: List<MovieResponse.Item>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieResultVH {
        val binding: ItemMovieBinding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieResultVH(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MovieResultVH, position: Int) {
        holder.bind(this.items[position])
    }

    inner class MovieResultVH(@NonNull private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemClick(items[adapterPosition].link)
            }
        }

        fun bind(item: MovieResponse.Item) {
            with(binding) {
                Glide.with(root.context).load(item.image).into(ivMovieImage)
                tvMovieName.text = item.title.getHtmlText()
                rbMovieRating.rating = item.userRating / 2f
                tvMoviePublishDate.text = item.pubDate
                tvMovieDirector.text = item.director
                tvMovieActor.text = item.actor
            }
        }
    }

    private fun String.getHtmlText(): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(this)
        }
    }
}