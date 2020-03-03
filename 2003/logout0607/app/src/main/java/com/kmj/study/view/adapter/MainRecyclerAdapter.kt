package com.kmj.study.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kmj.study.R
import com.kmj.study.dto.MovieDto
import kotlinx.android.synthetic.main.adapter_main.view.*
import java.util.*

class MainRecyclerAdapter(
    private val context: Context,
    private val items: ArrayList<MovieDto>,
    private val itemClick: (String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_main, parent, false)

        return MainViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? MainViewHolder)?.onBind(context, items[position], position)
    }

    inner class MainViewHolder(itemView: View, itemClick: (String) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        fun onBind(context: Context, item: MovieDto, position: Int) {
            with(itemView) {
                Glide.with(context).load(item.image).into(iv_image)
                tv_title.text = item.title
                rb_rating.rating = item.userRating.toFloat()
                tv_pub_date.text = item.pubDate
                tv_director.text = item.director
                tv_actor.text = item.actor
                tv_subtitle.text = item.subtitle

                layout_parent.setOnClickListener {
                    itemClick(item.link)
                }

                Log.d("BB", item.toString())
            }
        }
    }
}