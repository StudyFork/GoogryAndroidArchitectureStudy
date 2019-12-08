package com.example.kotlinapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.adapter.viewholder.KinViewHolder
import com.example.kotlinapplication.model.KinItems

class ListKinAdapter(
    listener: ItemListener

) :
    RecyclerView.Adapter<KinViewHolder>() {

    private var listener: ItemListener = listener
    private lateinit var items: List<KinItems>

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

    fun addAllItems(ListItems: List<KinItems>) {
        items = ListItems
    }

    interface ItemListener {
        fun onKinItemClick(kinItems: KinItems)
    }


}