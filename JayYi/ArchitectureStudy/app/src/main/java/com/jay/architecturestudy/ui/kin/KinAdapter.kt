package com.jay.architecturestudy.ui.kin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.jay.architecturestudy.R
import com.jay.architecturestudy.data.model.Kin
import com.jay.architecturestudy.databinding.ListItemKinBinding
import com.jay.architecturestudy.ui.BaseAdapter
import com.jay.architecturestudy.ui.BaseViewHolder
import com.jay.architecturestudy.util.startWebView

internal class KinAdapter : BaseAdapter<Kin, KinHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KinHolder {
        val binding = DataBindingUtil.inflate<ListItemKinBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item_kin,
            parent,
            false
        )
        return KinHolder(binding)
    }
}

internal class KinHolder(
    val binding: ListItemKinBinding
) : BaseViewHolder<Kin>(binding) {
    lateinit var item: Kin

    init {
        itemView.setOnClickListener { view ->
            view.startWebView(item.link)
        }
    }

    override fun bind(item: Kin) {
        this.item = item

        binding.description = item.description
        binding.title = item.title
        binding.invalidateAll()
    }
}