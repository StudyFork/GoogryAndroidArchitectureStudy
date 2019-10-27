package com.practice.achitecture.myproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practice.achitecture.myproject.model.Item
import com.practice.achitecture.myproject.utils.MyStringUtil
import kotlinx.android.synthetic.main.item_blog_and_news.view.tv_description
import kotlinx.android.synthetic.main.item_blog_and_news.view.tv_title
import kotlinx.android.synthetic.main.item_book_and_movie.view.*

class SearchMovieAndBookAdapter(private val items: ArrayList<Item>) :
    RecyclerView.Adapter<SearchMovieAndBookAdapter.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val listener = View.OnClickListener { it ->
            Toast.makeText(it.context, "Clicked: ${item.title}", Toast.LENGTH_SHORT).show()
        }
        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val inflatedView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_book_and_movie, parent, false)


        return ViewHolder(inflatedView)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v

        fun bind(listener: View.OnClickListener, item: Item?) {

            item?.image?.let {
                Glide
                    .with(view.context)
                    .load(it)
                    .centerCrop()
                    .into(view.iv_main_image)
            }

            view.tv_title.text = MyStringUtil.removeHtmlTags(item?.title)
            view.tv_description.text =
                MyStringUtil.removeHtmlTags(item?.description ?: item?.director)
            view.setOnClickListener(listener)
        }
    }
}