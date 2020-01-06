package com.example.androidarchitecture.ui.kin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.androidarchitecture.data.response.KinData
import com.example.androidarchitecture.databinding.ItemKinBinding
import com.example.androidarchitecture.ui.WebviewActivity
import com.example.androidarchitecture.ui.base.BaseRecyclerAdapter
import com.example.androidarchitecture.ui.base.BaseViewHolder


class KinAdapter : BaseRecyclerAdapter<KinData, KinAdapter.KinHolder>(DiffCallback()) {
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KinHolder {
        this.context = parent.context
        return KinHolder(ItemKinBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    inner class KinHolder(private val binding: ItemKinBinding) :
        BaseViewHolder<KinData>(binding.root) {
        lateinit var item: KinData

        init {
            binding.setOnClick {
                Intent(context, WebviewActivity::class.java).apply {
                    putExtra("link", item.link)
                }.run { context.startActivity(this) }

            }
        }

        override fun bind(item: KinData) {
            this.item = item
            with(binding) {
                items = item
                executePendingBindings()
            }
        }


    }

    private class DiffCallback : DiffUtil.ItemCallback<KinData>() {
        override fun areItemsTheSame(oldItem: KinData, newItem: KinData): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun areContentsTheSame(oldItem: KinData, newItem: KinData): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}