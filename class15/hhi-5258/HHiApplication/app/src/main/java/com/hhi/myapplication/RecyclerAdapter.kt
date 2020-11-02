package com.hhi.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hhi.myapplication.api.MovieData
import kotlinx.android.synthetic.main.main_recycler_item.view.*

class RecyclerAdapter() :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    var movieList: List<MovieData.MovieItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.main_recycler_item, parent, false)
        return RecyclerAdapter.ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        fun bind(data: MovieData.MovieItem) {
            Glide.with(itemView.context).load(data.image)
                .into(itemView.item_img_image)
            if (data.director.isNotEmpty()) {
                itemView.item_text_director.text = "감독 : " + data.director
            } else {
                itemView.item_text_director.text = ""
            }
            if (data.actor.isNotEmpty()) {
                itemView.item_text_actors.text = "출연 : " + data.actor
            } else {
                itemView.item_text_actors.text = ""
            }
            itemView.item_text_title.text = data.title.replace("<b>", "").replace("</b>", "")
        }
    }
}