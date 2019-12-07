package com.jay.architecturestudy.ui.kin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import com.jay.architecturestudy.R
import com.jay.architecturestudy.model.Kin
import com.jay.architecturestudy.ui.BaseAdapter
import com.jay.architecturestudy.ui.BaseViewHolder
import com.jay.architecturestudy.util.startWebView
import kotlinx.android.synthetic.main.list_item_kin.view.*

internal class KinAdapter : BaseAdapter<Kin, KinHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KinHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_kin, parent, false)
        return KinHolder(view)
    }
}

internal class KinHolder(
    view: View
) : BaseViewHolder<Kin>(view) {
    lateinit var item: Kin

    init {
        itemView.setOnClickListener { view ->
            view.startWebView(item.link)
        }
    }

    override fun bind(item: Kin) {
        this.item = item
        
        with(itemView) {
            kin_title.text = HtmlCompat.fromHtml(item.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
            kin_description.text =
                HtmlCompat.fromHtml(item.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
        }
    }
}