package com.example.study

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.rv_item.view.*
import java.util.ArrayList

class RecyclerAdapter :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {
    private var item = mutableListOf<NaverApiData.Item>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text =
            HtmlCompat.fromHtml(item[position].title, HtmlCompat.FROM_HTML_MODE_LEGACY)
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

    fun setItem(apiItem: List<NaverApiData.Item>) {
        item.clear()
        item.addAll(apiItem)
        notifyDataSetChanged()

    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.tv_title
        val subtitle: TextView = view.tv_subtitle
        val image: ImageView = view.iv_image
    }
}

