package com.lllccww.studyforkproject.view.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lllccww.studyforkproject.R
import com.lllccww.studyforkproject.model.MovieItem
import kotlinx.android.synthetic.main.item_movie_list.view.*

class MovieListAdapter(val context: Context) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    private val list: ArrayList<MovieItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        Glide.with(context).load(item.image).error(R.drawable.ic_no_img).into(holder.ivMovieImage)

        holder.tvTitle.text = android.text.Html.fromHtml(item.title).toString()
        holder.tvDirector.text = item.director
        holder.tvPubDate.text = item.pubDate
        holder.llParentView.setOnClickListener {

            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
            context.startActivity(intent)

        }


    }

    fun setItems(items: ArrayList<MovieItem>) {
        list.addAll(items)
        Log.d("movieData : ", list.size.toString())
        notifyDataSetChanged()
    }

    fun clear() {
        list.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.tv_title
        val tvDirector = itemView.tv_director
        val tvPubDate = itemView.tv_pubdate
        val ivMovieImage = itemView.iv_movie_image
        val llParentView = itemView.ll_parent_view
    }
}