package com.hwaniiidev.architecture.ui.main

import android.content.Intent
import android.net.Uri
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hwaniiidev.architecture.R
import com.hwaniiidev.architecture.databinding.ItemMovieListBinding
import com.hwaniiidev.architecture.model.Item


class AdapterMovieList :
    RecyclerView.Adapter<AdapterMovieList.MovieHolder>() {
    val movieList: ArrayList<Item> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        /*val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)*/

        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemMovieListBinding>(
            inflater,
            R.layout.item_movie_list,
            parent,
            false
        )


        val holder = MovieHolder(binding)
        //클릭시 브라우저로 영화 정보 조회
        holder.itemView.setOnClickListener { v ->
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse(movieList.get(holder.adapterPosition).link))
            holder.itemView.context.startActivity(intent)
        }
        return holder
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val itemData = movieList.get(position)
        /*if (itemData.image.isNullOrEmpty()) {
            Glide.with(holder.itemView)
                .load(R.drawable.gm_noimage)
                .into(holder.imgMovie)
        } else {
            Glide.with(holder.itemView)
                .load(itemData.image)
                .into(holder.imgMovie)
        }*/
        holder.binding.movieItem = itemData
        holder.binding.executePendingBindings()

        /*holder.txtTile.text = Html.fromHtml(itemData.title)
        holder.txtSubTile.text = itemData.subtitle
        holder.txtPubDate.text = itemData.pubDate
        holder.txtDirector.text = itemData.director
        holder.txtActors.text = itemData.actor
        holder.txtRating.text = itemData.userRating*/
    }

    fun addItem(items: List<Item>) {
        movieList.clear()
        movieList.addAll(items)
        notifyDataSetChanged()
    }

    inner class MovieHolder(val binding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        /*val imgMovie = itemView.findViewById<ImageView>(R.id.item_movie_list_image)
        val txtTile = itemView.findViewById<TextView>(R.id.item_movie_list_title)
        val txtSubTile = itemView.findViewById<TextView>(R.id.item_movie_list_subtitle)
        val txtPubDate = itemView.findViewById<TextView>(R.id.item_movie_list_pub_date)
        val txtDirector = itemView.findViewById<TextView>(R.id.item_movie_list_director)
        val txtActors = itemView.findViewById<TextView>(R.id.item_movie_list_actors)
        val txtRating = itemView.findViewById<TextView>(R.id.item_movie_list_rating)*/
    }

}