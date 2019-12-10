package wooooooak.com.studyapp.ui.base

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

abstract class BaseSearchListAdapter<T, H : RecyclerView.ViewHolder>(
    private val fragmentActivity: FragmentActivity,
    diffCallback: DiffUtil
    .ItemCallback<T>
) :
    ListAdapter<T, H>(diffCallback), Searchable {

    private var textOnEditTextView = ""

    override fun onBindViewHolder(holder: H, position: Int) {
        bindItemViewHolder(holder, position)
        if (position == itemCount - 1) loadMore(itemCount)
    }

    override fun searchByTitle(title: String) {
        fragmentActivity.lifecycleScope.launch {
            try {
                val itemList = initItemListByTitleAsync(title)
                submitList(itemList)
            } catch (e: Exception) {
                Toast.makeText(fragmentActivity, e.message, Toast.LENGTH_SHORT).show()
            } finally {
                textOnEditTextView = title
            }
        }
    }

    private fun loadMore(itemCount: Int) {
        fragmentActivity.lifecycleScope.launch {
            try {
                val addedList = getMoreItemListFromStartIndexAsync(textOnEditTextView, itemCount + 1)
                currentList.toMutableList().run {
                    addAll(addedList)
                    submitList(this)
                }
            } catch (e: Exception) {
                Toast.makeText(fragmentActivity, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    protected abstract fun bindItemViewHolder(holder: H, position: Int)
    protected abstract suspend fun initItemListByTitleAsync(title: String): List<T>
    protected abstract suspend fun getMoreItemListFromStartIndexAsync(title: String, startIndex: Int): List<T>
}