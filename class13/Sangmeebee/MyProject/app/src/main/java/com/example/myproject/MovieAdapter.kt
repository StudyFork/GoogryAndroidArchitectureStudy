package com.example.myproject

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
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
        holder.bind(movieArrayList[position])

        holder.llMovie!!.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(item.link)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = movieArrayList.size

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        private val llMovie = itemView?.findViewById<LinearLayout>(R.id.movie_item)
        private val ivPoster = itemView?.findViewById<ImageView>(R.id.iv_poster)
        private val tvTitle = itemView?.findViewById<TextView>(R.id.tv_title)
        private val rbGradeRating = itemView?.findViewById<RatingBar>(R.id.rb_grade_rating)
        private val tvDate = itemView?.findViewById<TextView>(R.id.tv_date)
        private val tvDirector = itemView?.findViewById<TextView>(R.id.tv_director)
        private val tvActor = itemView?.findViewById<TextView>(R.id.tv_actor)

        fun bind(item: Items) {

            tvTitle!!.text = interpretHtml(item.title)
            rbGradeRating!!.rating = item.userRating.toFloat() / 2
            tvDate!!.text = item.pubDate.toString()
            tvDirector!!.text = interpretHtml(item.director)
            tvActor!!.text = interpretHtml(item.actor)

            Glide.with(context)
                .load(item.image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivPoster!!)
        }
    }

    fun clearItems() {
        movieArrayList.clear()
        notifyDataSetChanged()
    }

    fun clearAndAddItems(items: ArrayList<Items>) {
        movieArrayList.clear()
        movieArrayList.addAll(items)
        notifyDataSetChanged()
    }

    private fun interpretHtml(str: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            Html.fromHtml(str, Html.FROM_HTML_MODE_LEGACY)
        else
            Html.fromHtml(str)
    }
}