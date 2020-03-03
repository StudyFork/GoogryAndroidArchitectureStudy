package com.mtjin.androidarchitecturestudy.ui

import android.view.LayoutInflater
import android.view.View
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        val viewHolder = ViewHolder(view)
        view.setOnClickListener{
            clickListener?.onItemClick(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].let {
            holder.bind(it)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var ivPoster: ImageView? = itemView.findViewById(R.id.iv_poster)
        private var rvRating: RatingBar? = itemView.findViewById(R.id.rb_rating)
        private var tvTitle: TextView? = itemView.findViewById(R.id.tv_title)
        private var tvReleaseDate: TextView? = itemView.findViewById(R.id.tv_release_date)
        private var tvActor: TextView? = itemView.findViewById(R.id.tv_actor)
        private var tvDirector: TextView? = itemView.findViewById(R.id.tv_director)

        fun bind(movie: Movie) {
            with(movie) {
                Glide.with(itemView).load(image).placeholder(R.drawable.ic_default).into(ivPoster!!)
                rvRating?.rating = userRating.toFloat() / 2
                tvTitle?.text = title
                tvReleaseDate?.text = pubDate
                tvActor?.text = actor
                tvDirector?.text = director
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