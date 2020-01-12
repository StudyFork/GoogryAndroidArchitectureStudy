package com.example.androidarchitecture.ui.image

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.androidarchitecture.common.StringConst.Companion.INTENT_KEY_LINK
import com.example.androidarchitecture.data.response.ImageData
import com.example.androidarchitecture.databinding.ItemImageBinding
import com.example.androidarchitecture.ui.WebviewActivity
import com.example.androidarchitecture.ui.base.BaseRecyclerAdapter
import com.example.androidarchitecture.ui.base.BaseViewHolder


class ImageAdapter :
    BaseRecyclerAdapter<ImageData, ImageAdapter.ImageHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ImageHolder(
        ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    inner class ImageHolder(private val binding: ItemImageBinding) :
        BaseViewHolder<ImageData>(binding.root) {
        private lateinit var item: ImageData

        init {
            binding.setOnClick {
                Intent(it.context, WebviewActivity::class.java).apply {
                    putExtra(INTENT_KEY_LINK, item.link)
                }.run { it.context.startActivity(this)
                }
            }
        }

        override fun bind(item: ImageData) {
            this.item = item
            with(binding) {
                items = item
                executePendingBindings()
            }
        }


    }

    private class DiffCallback : DiffUtil.ItemCallback<ImageData>() {
        override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}