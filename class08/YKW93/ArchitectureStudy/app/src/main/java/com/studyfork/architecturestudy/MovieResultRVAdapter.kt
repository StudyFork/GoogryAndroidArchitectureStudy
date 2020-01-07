package com.studyfork.architecturestudy

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieResultRVAdapter(val itemClick: (movieLink: String) -> Unit) :
    RecyclerView.Adapter<MovieResultRVAdapter.MovieResultVH>() {

    val items = mutableListOf<MovieResponse.Item>()

    fun setItems(items: List<MovieResponse.Item>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieResultVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieResultVH(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MovieResultVH, position: Int) {
        holder.bind(this.items[position])
    }


    inner class MovieResultVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                itemClick(items[adapterPosition].link)
            }
        }

        fun bind(item: MovieResponse.Item) {
            with(itemView) {
                Glide.with(context).load(item.image).into(iv_movie_image)
                tv_movie_name.text = item.title.getHtmlText()
                rb_movie_rating.rating = item.userRating / 2f
                tv_movie_publish_date.text = item.pubDate
                tv_movie_director.text = item.director
                tv_movie_actor.text = item.actor
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