package wooooooak.com.studyapp.ui.image

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import kotlinx.android.synthetic.main.item_image.view.*
import wooooooak.com.studyapp.R
import wooooooak.com.studyapp.common.ext.startWebView
import wooooooak.com.studyapp.data.model.repository.NaverApiRepositoryImpl
import wooooooak.com.studyapp.data.model.response.image.Image
import wooooooak.com.studyapp.ui.base.BaseSearchListAdapter

class ImageListAdapter(private val fragmentActivity: FragmentActivity) :
    BaseSearchListAdapter<Image, ImageListAdapter.ImageViewHolder>(fragmentActivity, DiffCallback()) {

    private val naverApiRepository = NaverApiRepositoryImpl()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ImageViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_image, parent, false)
    )

    override fun bindItemViewHolder(holder: ImageViewHolder, position: Int) {
        getItem(position)?.let { image ->
            holder.bind(image, View.OnClickListener {
                fragmentActivity.startWebView(image.link)
            })
        }
    }

    override suspend fun initItemListByTitleAsync(title: String) =
        naverApiRepository.getImageList(title)

    override suspend fun getMoreItemListFromStartIndexAsync(title: String, startIndex: Int) =
        naverApiRepository.getImageList(title, startIndex)

    inner class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(image: Image, onClickItem: View.OnClickListener) {
            with(itemView) {
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