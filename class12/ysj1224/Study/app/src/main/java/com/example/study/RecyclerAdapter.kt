package com.example.study

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.rv_item.view.*

class RecyclerAdapter(apiItem: List<NaverApiData.Item>) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {
    var item = apiItem

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = item[position].title
        holder.subtitle.text = item[position].subtitle
        Glide.with(holder.image.context)
            .load(item[position].image)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title = view.tv_title
        var subtitle = view.tv_subtitle
        var image = view.iv_image
    }
}

