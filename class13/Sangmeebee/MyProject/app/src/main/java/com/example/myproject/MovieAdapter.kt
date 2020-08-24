package com.example.myproject

import android.content.ClipData.Item
import android.content.Context
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myproject.MovieAdapter.ViewHolder
import com.example.myproject.retrofit.model.Items


class MovieAdapter(val context: Context, val movieArrayList: ArrayList<Items>) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movieArrayList[position]
        holder.tvTitle!!.text = interpretHtml(item.title)
        holder.rbGradeRating!!.rating = item.userRating.toFloat() / 2
        holder.tvDate!!.text = item.pubDate.toString()
        holder.tvDirector!!.text = interpretHtml(item.director)
        holder.tvActor!!.text = interpretHtml(item.actor)

        Glide.with(context)
            .load(item.image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.getImage()!!)
    }

    override fun getItemCount() = movieArrayList.size

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val ivPoster = itemView?.findViewById<ImageView>(R.id.iv_poster)
        val tvTitle = itemView?.findViewById<TextView>(R.id.tv_title)
        val rbGradeRating = itemView?.findViewById<RatingBar>(R.id.rb_grade_rating)
        val tvDate = itemView?.findViewById<TextView>(R.id.tv_date)
        val tvDirector = itemView?.findViewById<TextView>(R.id.tv_director)
        val tvActor = itemView?.findViewById<TextView>(R.id.tv_actor)

        fun getImage(): ImageView? {
            return ivPoster
        }
    }

    fun interpretHtml(str: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            Html.fromHtml(str, Html.FROM_HTML_MODE_LEGACY)
        else
            Html.fromHtml(str)
    }
}