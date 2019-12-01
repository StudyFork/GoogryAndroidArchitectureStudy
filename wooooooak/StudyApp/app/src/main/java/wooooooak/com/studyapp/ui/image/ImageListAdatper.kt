package wooooooak.com.studyapp.ui.image

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.fragment.app.FragmentActivity
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
import wooooooak.com.studyapp.common.ext.startWebView
import wooooooak.com.studyapp.model.response.image.Image
import wooooooak.com.studyapp.naverApi
import wooooooak.com.studyapp.ui.base.Searchable

class ImageListAdapter(private val fragmentActivity: FragmentActivity) :
    ListAdapter<Image, ImageListAdapter.ImageViewHolder>(DiffCallback()), Searchable {

    private var textOnEditTextView = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ImageViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_image, parent, false)
    )

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        getItem(position)?.let { image ->
            holder.bind(image, View.OnClickListener {
                fragmentActivity.startWebView(image.link)
            })
        }
        if (position > itemCount - PAGING_OFFSET) loadMore()
    }

    override fun searchByTitle(title: String) {
        fragmentActivity.lifecycleScope.launch {
            try {
                val response = naverApi.getImages(title, DISPLAY_LIST_COUNT, null)
                submitList(response.images)
            } catch (e: Exception) {
                Toast.makeText(fragmentActivity, e.toString(), Toast.LENGTH_SHORT).show()
            } finally {
                textOnEditTextView = title
            }
        }
    }

    private fun loadMore() {
        fragmentActivity.lifecycleScope.launch {
            try {
                val response = naverApi.getImages(textOnEditTextView, DISPLAY_LIST_COUNT, itemCount + 1)
                currentList.toMutableList().let { list ->
                    list.addAll(response.images)
                    submitList(list)
                }
            } catch (e: Exception) {
                Toast.makeText(fragmentActivity, e.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    inner class ImageViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(image: Image, onClickItem: View.OnClickListener) {
            with(view) {
                title.text = HtmlCompat.fromHtml(image.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
                image_view.load(image.thumbnail)
                image_card.setOnClickListener(onClickItem)
            }
        }
    }

}

private class DiffCallback : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: Image, newItem: Image) =
        oldItem == newItem
}