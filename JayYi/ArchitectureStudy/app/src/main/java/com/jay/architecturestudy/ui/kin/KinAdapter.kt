package com.jay.architecturestudy.ui.kin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import com.jay.architecturestudy.R
import com.jay.architecturestudy.model.Kin
import com.jay.architecturestudy.ui.BaseAdapter
import com.jay.architecturestudy.ui.BaseViewHolder
import kotlinx.android.synthetic.main.list_item_kin.view.*

internal class KinAdapter : BaseAdapter<Kin, KinHolder>() {
    private val data = arrayListOf<Kin>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KinHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_kin, parent, false)
        return KinHolder(view)
    }

    override fun setData(items: List<Kin>) {
        super.setData(items)
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
}

internal class KinHolder(
    view: View
) : BaseViewHolder<Kin>(view) {

    override fun bind(item: Kin) {
        with(itemView) {
            kin_title.text = HtmlCompat.fromHtml(item.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
            kin_description.text =
                HtmlCompat.fromHtml(item.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
        }
    }
}