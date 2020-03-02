package com.mtjin.androidarchitecturestudy.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mtjin.androidarchitecturestudy.R
import com.mtjin.androidarchitecturestudy.data.Movie

class MovieAdapter :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    var clickListener: ItemClickListener? = null
    private var items: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie,
                parent,
                false
            )
        ) {
        private var ivPoster: ImageView? = null
        private var rvRating: RatingBar? = null
        private var tvTitle: TextView? = null
        private var tvReleaseDate: TextView? = null
        private var tvActor: TextView? = null
        private var tvDirector: TextView? = null

        init {
            ivPoster = itemView.findViewById(R.id.iv_poster)
            rvRating = itemView.findViewById(R.id.rb_rating)
            tvTitle = itemView.findViewById(R.id.tv_title)
            tvReleaseDate = itemView.findViewById(R.id.tv_release_date)
            tvActor = itemView.findViewById(R.id.tv_actor)
            tvDirector = itemView.findViewById(R.id.tv_director)
        }

        fun bind(movie: Movie) {
            with(movie) {
                Glide.with(itemView).load(image).placeholder(R.drawable.ic_default).into(ivPoster!!)
                rvRating?.rating = userRating.toFloat() / 2
                tvTitle?.text = title
                tvReleaseDate?.text = pubDate
                tvActor?.text = actor
                tvDirector?.text = director
                itemView.setOnClickListener {
                    clickListener?.onItemClick(movie)
                }
            }
        }
    }

    fun setItems(items: List<Movie>) {
        this.items = items.toMutableList()
    }

    fun setItemClickListener(listener: ItemClickListener?) {
        this.clickListener = listener
    }

    fun clear() {
        this.items.clear()
    }

    interface ItemClickListener {
        fun onItemClick(movie: Movie)
    }
}