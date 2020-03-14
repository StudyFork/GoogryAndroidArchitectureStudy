package com.example.kangraemin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kangraemin.R
import com.example.kangraemin.model.remote.datamodel.MovieDetail
import com.example.kangraemin.util.Utils
import kotlinx.android.synthetic.main.adapter_search_result.view.*

class SearchResultAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = ArrayList<MovieDetail>()

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

    fun setData(data: ArrayList<MovieDetail>) {
        this.data.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val result = data[position]
        holder.apply {
            itemView.tv_castings.text = result.actor
            itemView.tv_director.text = result.director
            itemView.tv_title.text = Utils.fromHtml(result.title)
            itemView.tv_date.text = result.pubDate
            itemView.rtb_rating.rating = result.userRating.toFloat() / 2
            Glide.with(itemView.img_thumbnail)
                .load(result.image)
                .centerCrop()
                .into(itemView.img_thumbnail)
        }
    }
}