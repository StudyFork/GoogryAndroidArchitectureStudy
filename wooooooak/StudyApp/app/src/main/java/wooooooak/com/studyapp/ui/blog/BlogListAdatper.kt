package wooooooak.com.studyapp.ui.blog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_blog.view.*
import kotlinx.android.synthetic.main.item_image.view.title
import wooooooak.com.studyapp.R
import wooooooak.com.studyapp.data.model.response.blog.Blog
import wooooooak.com.studyapp.ui.base.BaseSearchListAdapter

class BlogSearchListAdapter(
    private val itemListener: ItemListener<Blog>
) :
    BaseSearchListAdapter<Blog, BlogSearchListAdapter.BlogViewHolder>(itemListener, DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BlogViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_blog, parent, false)
    )

    override fun bindItemViewHolder(holder: BlogViewHolder, position: Int) {
        getItem(position)?.let { blog ->
            holder.bind(blog, View.OnClickListener {
                itemListener.renderWebView(blog.link)
            })
        }
    }

    inner class BlogViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(blog: Blog, onClickItem: View.OnClickListener) {
            with(itemView) {
                title.text = HtmlCompat.fromHtml(blog.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
                description.text = HtmlCompat.fromHtml(blog.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
                blogger_name.text = blog.bloggerName
                blog_card.setOnClickListener(onClickItem)
            }
        }
    }

}

private class DiffCallback : DiffUtil.ItemCallback<Blog>() {
    override fun areItemsTheSame(oldItem: Blog, newItem: Blog) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Blog, newItem: Blog) =
        oldItem == newItem
}