package com.example.kotlinapplication.adapter.viewholder

import android.os.Build
import android.text.Html
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.adapter.ListKinAdapter
import com.example.kotlinapplication.model.KinItems
import kotlinx.android.synthetic.main.kin_list_item.view.*

class KinViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: KinItems, listener: ListKinAdapter.ItemListener?) {
        itemView.kin_item_layout.setOnClickListener {
            listener!!.onKinItemClick(item)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            itemView.kin_item_title.text = Html.fromHtml(item.title, 0)
            itemView.kin_item_description.text = Html.fromHtml(item.description, 0)
        } else {
            itemView.kin_item_title.text = item.title
            itemView.kin_item_description.text = item.description
        }
    }

}