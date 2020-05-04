package com.lllccww.studyforkproject.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lllccww.studyforkproject.R
import com.lllccww.studyforkproject.model.MovieItem
import kotlinx.android.synthetic.main.item_movie_list.view.*

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    private val list: ArrayList<MovieItem> = ArrayList()
    private lateinit var callback: (MovieItem) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)
        val viewHolder = ViewHolder(view)
        view.setOnClickListener {
            callback(list[viewHolder.adapterPosition])
        }

        return viewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)


    }

    fun setItems(items: ArrayList<MovieItem>) {
        list.addAll(items)
        Log.d("movieData : ", list.size.toString())
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    fun setItemClickListener(callback: (MovieItem) -> Unit) {
        this.callback = callback
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle = itemView.tv_title!!
        private val tvDirector = itemView.tv_director!!
        private val tvPubDate = itemView.tv_pubdate!!
        private val ivMovieImage = itemView.iv_movie_image!!

        fun bind(movieItem: MovieItem) {
            Glide.with(itemView).load(movieItem.image).error(R.drawable.ic_no_img)
                .into(ivMovieImage)

            tvTitle.text = android.text.Html.fromHtml(movieItem.title).toString()
            tvDirector.text = movieItem.director
            tvPubDate.text = movieItem.pubDate
        }


    }
}