package wooooooak.com.studyapp.ui.blog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import wooooooak.com.studyapp.data.model.response.blog.Blog
import wooooooak.com.studyapp.databinding.ItemBlogBinding
import wooooooak.com.studyapp.ui.base.BaseSearchListAdapter

class BlogSearchListAdapter(
    private val itemListener: ItemListener<Blog>
) :
    BaseSearchListAdapter<Blog, BlogSearchListAdapter.BlogViewHolder>(itemListener, DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BlogViewHolder(
        ItemBlogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun bindItemViewHolder(holder: BlogViewHolder, position: Int) {
        getItem(position)?.let { blog ->
            holder.bind(blog, View.OnClickListener {
                itemListener.renderWebView(blog.link)
            })
        }
    }

    inner class BlogViewHolder(private val binding: ItemBlogBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(blog: Blog, _onClickItemListener: View.OnClickListener) {
            with(binding) {
                item = blog
                onClickItemListener = _onClickItemListener
                executePendingBindings()
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