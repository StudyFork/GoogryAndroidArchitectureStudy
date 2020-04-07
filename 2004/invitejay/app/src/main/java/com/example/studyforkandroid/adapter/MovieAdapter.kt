package com.example.studyforkandroid.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.studyforkandroid.R
import com.example.studyforkandroid.data.Item
import com.example.studyforkandroid.utils.htmlToString
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(val context: Context) : RecyclerView.Adapter<MovieAdapter.MovieVh>() {

    private var movieList: List<Item> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MovieVh {
        return MovieVh(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setList(movieList: List<Item>) {
        this.movieList = movieList
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieVh, position: Int) {
        movieList[position].let { Item ->
            with(holder) {
                title.text = Item.title.htmlToString()
                rating.text = Item.userRating.htmlToString()
                actor.text = Item.actor.htmlToString().replace("|", " ")
            }
        }

        Glide.with(context).load(movieList[position].image).into(holder.img)
        holder.layout.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(movieList[position].link)
            context.startActivity(intent)
        }
    }

    class MovieVh(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.movieImg
        val title: TextView = itemView.movieTitle
        val actor: TextView = itemView.movieActor
        val rating: TextView = itemView.movieRating
        val layout: LinearLayout = itemView.movieLayout
    }
}