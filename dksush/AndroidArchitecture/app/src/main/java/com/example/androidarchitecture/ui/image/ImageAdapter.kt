package com.example.androidarchitecture.ui.image

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.androidarchitecture.data.response.ImageData
import com.example.androidarchitecture.databinding.ItemImageBinding
import com.example.androidarchitecture.ui.WebviewActivity
import com.example.androidarchitecture.ui.base.BaseRecyclerAdapter
import com.example.androidarchitecture.ui.base.BaseViewHolder


class ImageAdapter :
    BaseRecyclerAdapter<ImageData, ImageAdapter.ImageHolder>(DiffCallback()) {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        context = parent.context
        return ImageHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ImageHolder(private val binding: ItemImageBinding) :
        BaseViewHolder<ImageData>(binding.root) {
        lateinit var item: ImageData

        init {
            binding.setOnClick {
                Intent(context, WebviewActivity::class.java).apply {
                    // apply 끝에 그러니까 run 자리에서 뱉는건, intent 객체.  안에 애들은 객체를 뱉기전에 값넣기 혹은 초기화 작업시 좋다.
                    putExtra("link", item.link)
                }.run { context.startActivity(this) }
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