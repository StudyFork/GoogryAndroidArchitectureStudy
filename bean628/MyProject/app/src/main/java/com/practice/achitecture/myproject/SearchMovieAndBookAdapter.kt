package com.practice.achitecture.myproject

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practice.achitecture.myproject.model.SearchedItem
import kotlinx.android.synthetic.main.item_blog_and_news.view.tv_description
import kotlinx.android.synthetic.main.item_blog_and_news.view.tv_title
import kotlinx.android.synthetic.main.item_book_and_movie.view.*

class SearchMovieAndBookAdapter(private val items: ArrayList<SearchedItem>) :
    RecyclerView.Adapter<SearchMovieAndBookAdapter.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val listener = View.OnClickListener { it ->
            val url = Uri.parse(item.link)
            val intent = Intent(Intent.ACTION_VIEW, url)
            it.context.startActivity(intent)
        }
        holder.run {
            bind(listener, item)
            itemView.tag = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book_and_movie, parent, false)


        return ViewHolder(inflatedView)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(listener: View.OnClickListener, item: SearchedItem?) {

            if (item?.image != null) {
                Glide
                    .with(itemView.context)
                    .load(item.image)
                    .centerCrop()
                    .placeholder(R.drawable.drawable_gray)
                    .error(R.drawable.ic_empty_image)
                    .into(itemView.iv_main_image)
            }

            val title = item?.title ?: ""
            val content = item?.description ?: item?.director ?: ""

            itemView.tv_title.text =
                HtmlCompat.fromHtml(title, HtmlCompat.FROM_HTML_MODE_COMPACT)
            itemView.tv_description.text = HtmlCompat.fromHtml(
                content,
                HtmlCompat.FROM_HTML_MODE_COMPACT
            )
            itemView.setOnClickListener(listener)
        }
    }
}