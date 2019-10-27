package com.practice.achitecture.myproject

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practice.achitecture.myproject.model.Item
import com.practice.achitecture.myproject.utils.MyStringUtil
import kotlinx.android.synthetic.main.item_blog_and_news.view.*

class SearchBlogAndNewsAdapter(private val items: ArrayList<Item>) :
    RecyclerView.Adapter<SearchBlogAndNewsAdapter.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: SearchBlogAndNewsAdapter.ViewHolder, position: Int) {
        val item = items[position]
        val listener = View.OnClickListener { it ->
            val url = Uri.parse(item.link)
            val intent = Intent(Intent.ACTION_VIEW, url)
            it.context.startActivity(intent)
        }
        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            SearchBlogAndNewsAdapter.ViewHolder {
        val inflatedView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_blog_and_news, parent, false)

        return SearchBlogAndNewsAdapter.ViewHolder(inflatedView)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v

        fun bind(listener: View.OnClickListener, item: Item?) {
            view.tv_title.text = MyStringUtil.removeHtmlTags(item?.title)
            view.tv_description.text = MyStringUtil.removeHtmlTags(item?.description)
            view.setOnClickListener(listener)
        }
    }
}