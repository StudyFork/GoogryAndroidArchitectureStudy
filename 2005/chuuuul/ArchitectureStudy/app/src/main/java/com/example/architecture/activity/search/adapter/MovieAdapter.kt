package com.example.architecture.activity.search.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.architecture.R
import com.example.architecture.data.model.MovieModel
import com.example.architecture.util.ConstValue.Companion.NO_IMAGE_URL
import kotlinx.android.synthetic.main.item_movie.view.*
import org.apache.commons.text.StringEscapeUtils
import org.jsoup.Jsoup

class MovieAdapter :  RecyclerView.Adapter<MovieViewHolder>() {

    private val movieList = mutableListOf<MovieModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        val viewHolder = MovieViewHolder(view)

        view.setOnClickListener {
            openWebPage(it.context, movieList[viewHolder.layoutPosition].link)
        }

        return viewHolder
    }

    private fun openWebPage(context: Context, link: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        context.startActivity(intent)
    }

    override fun getItemCount(): Int {
        return movieList.count()
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movie = movieList[position]
        holder.onBind( movie )
    }

    fun addNewItems(movieList: List<MovieModel>) {
        if (this.movieList.isNotEmpty()) {
            this.movieList.clear()
        }

        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }
}

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view)
{
    fun onBind(movie : MovieModel)
    {
        itemView.tv_movie_title.text = removeHtmlTag(movie.title)
        itemView.tv_movie_pubDate.text = movie.pubDate

        itemView.ratingBar_movie_rating.rating = movie.userRating

        setMovieImage(itemView.img_movie_Image, movie.image)

    }

    private fun removeHtmlTag(html: String): String {
        val escapedHtml = StringEscapeUtils.unescapeHtml4(html)
        return Jsoup.parse(escapedHtml).text()
    }

    private fun setMovieImage(imageView: ImageView, imageUrl: String) {
        val url = if (imageUrl.isBlank()) {
            NO_IMAGE_URL
        } else {
            imageUrl
        }
        Glide.with(imageView.context)
            .load(url).placeholder(R.drawable.ic_loading_black_24dp)
            .error(R.drawable.image_loaderror).centerCrop().into(imageView)
    }

}
