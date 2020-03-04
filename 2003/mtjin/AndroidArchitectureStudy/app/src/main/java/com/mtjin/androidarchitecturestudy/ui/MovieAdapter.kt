package com.mtjin.androidarchitecturestudy.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mtjin.androidarchitecturestudy.R
import com.mtjin.androidarchitecturestudy.data.Movie

class MovieAdapter :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    private lateinit var clickListener: ItemClickListener
    private val items: ArrayList<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        val viewHolder = ViewHolder(view)
        view.setOnClickListener {
            clickListener.onItemClick(items[viewHolder.adapterPosition])
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

        private val ivPoster = itemView.findViewById<ImageView>(R.id.iv_poster)
        private val rvRating = itemView.findViewById<RatingBar>(R.id.rb_rating)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        private val tvReleaseDate = itemView.findViewById<TextView>(R.id.tv_release_date)
        private val tvActor = itemView.findViewById<TextView>(R.id.tv_actor)
        private val tvDirector = itemView.findViewById<TextView>(R.id.tv_director)

        fun bind(movie: Movie) {
            with(movie) {
                Glide.with(itemView).load(image)
                    .placeholder(R.drawable.ic_default)
                    .into(ivPoster!!)
                rvRating.rating = (userRating.toFloatOrNull() ?: 0f) / 2
                tvTitle.text = HtmlCompat.fromHtml(title, HtmlCompat.FROM_HTML_MODE_COMPACT)
                tvReleaseDate.text = pubDate
                tvActor.text = actor
                tvDirector.text = director
            }
        }
    }

    fun setItems(items: List<Movie>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun setItemClickListener(listener: ItemClickListener) {
        this.clickListener = listener
    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onItemClick(movie: Movie)
    }
}