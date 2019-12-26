package com.example.kotlinapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.adapter.viewholder.KinViewHolder
import com.example.kotlinapplication.data.KinItem

class ListKinAdapter(
    val listener: ItemListener

) :
    RecyclerView.Adapter<KinViewHolder>() {

    private val items = arrayListOf<KinItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KinViewHolder {
        return KinViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.kin_list_item,
                parent,
                false
            )
        )


    }

    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: KinViewHolder, position: Int) {
        holder.bind(items[position], listener)

    }

    fun addAllItems(ListItems: List<KinItem>) {
        items.clear()
        items.addAll(ListItems)
        notifyDataSetChanged()
    }

    interface ItemListener {
        fun onKinItemClick(kinItems: KinItem)
    }


}