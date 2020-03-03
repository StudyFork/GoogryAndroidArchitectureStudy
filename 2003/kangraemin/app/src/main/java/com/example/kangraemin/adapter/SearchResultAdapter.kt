package com.example.kangraemin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kangraemin.R
import com.example.kangraemin.Response.ResponseMovieSearch
import com.example.kangraemin.util.Utils
import kotlinx.android.synthetic.main.adapter_search_result.view.*

class SearchResultAdapter(val context: Context, var data: List<ResponseMovieSearch>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val utils = Utils()

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_search_result,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val result = data[position]
        holder.itemView.tv_castings.text = result.actor
        holder.itemView.tv_director.text = result.director
        holder.itemView.tv_title.text = utils.fromHtml(result.title)
        holder.itemView.tv_date.text = result.pubDate
        Glide.with(context).load(result.image).centerCrop().into(holder.itemView.img_thumbnail)
        holder.itemView.rtb_rating.rating = result.userRating.toFloat() / 2
    }
}