package com.lllccww.studyforkproject.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lllccww.studyforkproject.R
import com.lllccww.studyforkproject.model.MovieItem
import kotlinx.android.synthetic.main.item_movie_list.view.*

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    private val list: ArrayList<MovieItem> = ArrayList()
    var onClick: ((MovieItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent).apply {
            itemView.setOnClickListener {
                val item = list[adapterPosition]
                onClick?.invoke(item)

            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])


    }

    fun addItems(items: ArrayList<MovieItem>) {
        list.addAll(items)
        Log.d("movieData : ", list.size.toString())
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_movie_list, parent, false
        )
    ) {
        private val tvTitle = itemView.tv_title!!
        private val tvDirector = itemView.tv_director!!
        private val tvPubDate = itemView.tv_pubdate!!
        private val ivMovieImage = itemView.iv_movie_image!!
        private val tvUserRating = itemView.tv_user_rating!!


        fun bind(movieItem: MovieItem) {
            Glide.with(itemView).load(movieItem.image).error(R.drawable.ic_no_img)
                .into(ivMovieImage)

            tvTitle.text = android.text.Html.fromHtml(movieItem.title).toString()
            tvDirector.text = movieItem.director
            tvPubDate.text = movieItem.pubDate
            tvUserRating.text = movieItem.userRating
        }


    }
}