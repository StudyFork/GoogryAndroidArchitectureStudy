package com.hjhan.hyejeong.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hjhan.hyejeong.R
import com.hjhan.hyejeong.data.model.Item

class MovieAdapter :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val list = mutableListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_movie, parent,
            false
        )

        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.bind(list[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setMovieList(list: List<Item>) {
        with(this.list) {
            clear()
            addAll(list)
        }

        notifyDataSetChanged()
    }

    class MovieViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        var title = itemView.findViewById(R.id.title_text_view) as TextView
        var subTitle = itemView.findViewById(R.id.sub_title_text_view) as TextView
        var director = itemView.findViewById(R.id.director_text_view) as TextView
        var image = itemView.findViewById(R.id.movie_image_image_view) as ImageView

        fun bind(data: Item) {

            title.text = data.title
            subTitle.text = data.subtitle
            director.text = data.director

            Glide.with(image.context)
                .load(data.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image)


        }
    }
}