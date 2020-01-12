package com.example.androidarchitecture.ui.kin

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.androidarchitecture.common.StringConst.Companion.INTENT_KEY_LINK
import com.example.androidarchitecture.data.response.KinData
import com.example.androidarchitecture.databinding.ItemKinBinding
import com.example.androidarchitecture.ui.WebviewActivity
import com.example.androidarchitecture.ui.base.BaseRecyclerAdapter
import com.example.androidarchitecture.ui.base.BaseViewHolder


class KinAdapter : BaseRecyclerAdapter<KinData, KinAdapter.KinHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = KinHolder(
        ItemKinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    inner class KinHolder(private val binding: ItemKinBinding) :
        BaseViewHolder<KinData>(binding.root) {
        private lateinit var item: KinData

        init {
            binding.setOnClick {
                Intent(it.context, WebviewActivity::class.java).apply {
                    putExtra(INTENT_KEY_LINK, item.link)
                }.run { it.context.startActivity(this) }

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