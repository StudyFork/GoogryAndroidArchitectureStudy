package wooooooak.com.studyapp.ui.image

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
import coil.api.load
import kotlinx.android.synthetic.main.item_image.view.*
import kotlinx.coroutines.launch
import wooooooak.com.studyapp.R
import wooooooak.com.studyapp.common.constants.DISPLAY_LIST_COUNT
import wooooooak.com.studyapp.common.constants.PAGING_OFFSET
import wooooooak.com.studyapp.model.response.image.Image
import wooooooak.com.studyapp.naverApi
import wooooooak.com.studyapp.ui.base.Searchable

class ImageListAdapter(private val lifecycleOwner: LifecycleOwner) :
    ListAdapter<Image, ImageListAdapter.ImageViewHolder>(DiffCallback()), Searchable {

    private var textOnEditTextView = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ImageViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_image, parent, false)
    )

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
        if (position > itemCount - PAGING_OFFSET) loadMore()
    }

    override fun searchByTitle(title: String) {
        lifecycleOwner.lifecycleScope.launch {
            try {
                val response = naverApi.getImages(title, DISPLAY_LIST_COUNT, null)
                submitList(response.images)
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
                val response = naverApi.getImages(textOnEditTextView, DISPLAY_LIST_COUNT, itemCount + 1)
                currentList.toMutableList().let { list ->
                    list.addAll(response.images)
                    submitList(list)
                }
            } catch (e: Exception) {

            }
        }
    }

    inner class ImageViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(image: Image) {
            view.title.text = HtmlCompat.fromHtml(image.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
            view.image_view.load(image.thumbnail)
        }
    }

}

private class DiffCallback : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Image, newItem: Image) =
        oldItem == newItem
}