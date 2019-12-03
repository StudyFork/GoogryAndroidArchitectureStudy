package wooooooak.com.studyapp.ui.blog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_blog.view.*
import kotlinx.android.synthetic.main.item_image.view.title
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import wooooooak.com.studyapp.R
import wooooooak.com.studyapp.common.constants.DISPLAY_LIST_COUNT
import wooooooak.com.studyapp.common.constants.PAGING_OFFSET
import wooooooak.com.studyapp.common.ext.startWebView
import wooooooak.com.studyapp.model.response.blog.Blog
import wooooooak.com.studyapp.naverApi
import wooooooak.com.studyapp.ui.base.BaseSearchListAdapter

class BlogSearchListAdapter(private val fragmentActivity: FragmentActivity) :
    BaseSearchListAdapter<Blog, BlogSearchListAdapter.BlogViewHolder>(fragmentActivity, DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BlogViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_blog, parent, false)
    )

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        getItem(position)?.let { blog ->
            holder.bind(blog, View.OnClickListener {
                fragmentActivity.startWebView(blog.link)
            })
        }
        if (position > itemCount - PAGING_OFFSET) loadMore(itemCount)
    }

    override suspend fun initItemListByTitleAsync(title: String): List<Blog> =
        withContext(Dispatchers.IO) {
            naverApi.getBlogs(title, DISPLAY_LIST_COUNT, null).items
        }

    override suspend fun getMoreItemListFromStartIndexAsync(title: String, startIndex: Int): List<Blog> =
        withContext(Dispatchers.IO) {
            naverApi.getBlogs(title, DISPLAY_LIST_COUNT, startIndex).items
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