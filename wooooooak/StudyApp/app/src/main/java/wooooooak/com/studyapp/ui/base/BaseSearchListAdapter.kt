package wooooooak.com.studyapp.ui.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseSearchListAdapter<T, H : RecyclerView.ViewHolder>(
    private val itemListener: ItemListener<T>,
    diffCallback: DiffUtil.ItemCallback<T>
) :
    ListAdapter<T, H>(diffCallback) {

    private var textOnEditTextView = ""

    override fun onBindViewHolder(holder: H, position: Int) {
        bindItemViewHolder(holder, position)
        if (position == itemCount - 1) itemListener.loadMoreItems(currentList, itemCount + 1)
    }

    protected abstract fun bindItemViewHolder(holder: H, position: Int)

    interface ItemListener<T> {
        fun loadMoreItems(list: List<T>, index: Int)

        fun renderWebView(url: String)
    }
}