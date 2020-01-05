package wooooooak.com.studyapp.ui.image

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import wooooooak.com.studyapp.data.model.response.image.Image
import wooooooak.com.studyapp.databinding.ItemImageBinding
import wooooooak.com.studyapp.ui.base.BaseSearchListAdapter

class ImageListAdapter(
    private val itemListener: ItemListener<Image>
) :
    BaseSearchListAdapter<Image, ImageListAdapter.ImageViewHolder>(itemListener, DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ImageViewHolder(
        ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun bindItemViewHolder(holder: ImageViewHolder, position: Int) {
        getItem(position)?.let { image ->
            holder.bind(image, View.OnClickListener {
                itemListener.renderWebView(image.link)
            })
        }
    }

    inner class ImageViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Image, _onClickItemListener: View.OnClickListener) {
            with(binding) {
                item = image
                onClickItemListener = _onClickItemListener
                executePendingBindings()
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