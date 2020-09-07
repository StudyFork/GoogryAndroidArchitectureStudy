package com.example.myproject.ui

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
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myproject.R
import com.example.myproject.data.model.Items
import com.example.myproject.ui.MovieAdapter.ViewHolder


class MovieAdapter(val context: Context, val movieArrayList: ArrayList<Items>) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        val result = ViewHolder(inflatedView)
        inflatedView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(movieArrayList[result.bindingAdapterPosition].link)
            context.startActivity(intent)
        }
        return result
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieArrayList[position])
    }

    override fun getItemCount() = movieArrayList.size

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

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
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
}
