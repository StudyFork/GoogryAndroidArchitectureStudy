package wooooooak.com.studyapp.ui.blog


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_blog.view.*
import kotlinx.android.synthetic.main.item_image.view.title
import kotlinx.coroutines.launch
import wooooooak.com.studyapp.R
import wooooooak.com.studyapp.common.constants.DISPLAY_LIST_COUNT
import wooooooak.com.studyapp.common.constants.PAGING_OFFSET
import wooooooak.com.studyapp.model.response.blog.Blog
import wooooooak.com.studyapp.naverApi
import wooooooak.com.studyapp.ui.base.Searchable

class BlogListAdapter(private val lifecycleOwner: LifecycleOwner) :
    ListAdapter<Blog, BlogListAdapter.BlogViewHolder>(DiffCallback()), Searchable {

    private var textOnEditTextView = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BlogViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_blog, parent, false)
    )

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
        if (position > itemCount - PAGING_OFFSET) loadMore()
    }

    override fun searchByTitle(title: String) {
        lifecycleOwner.lifecycleScope.launch {
            try {
                val response = naverApi.getBlogs(title, DISPLAY_LIST_COUNT, null)
                submitList(response.blogs)
            } catch (e: Exception) {
                Log.d("MovieFragment", e.toString())
                Log.d("MovieFragment", e.message.toString())
            } finally {
                textOnEditTextView = title
            }
        }
    }

    private fun loadMore() {
        lifecycleOwner.lifecycleScope.launch {
            try {
                val response = naverApi.getBlogs(textOnEditTextView, DISPLAY_LIST_COUNT, itemCount + 1)
                currentList.toMutableList().let { list ->
                    list.addAll(response.blogs)
                    submitList(list)
                }
            } catch (e: Exception) {

            }
        }
    }

    inner class BlogViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(blog: Blog) {
            with(view) {
                title.text = HtmlCompat.fromHtml(blog.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
                description.text = HtmlCompat.fromHtml(blog.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
                blogger_name.text = blog.bloggerName
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