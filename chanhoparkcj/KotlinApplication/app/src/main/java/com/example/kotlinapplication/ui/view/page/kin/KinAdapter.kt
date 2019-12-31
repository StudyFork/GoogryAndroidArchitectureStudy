package com.example.kotlinapplication.ui.view.page.kin

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.data.model.KinItem

class KinAdapter(private val listener: (KinItem)->Unit) : RecyclerView.Adapter<KinViewHolder>() {
    private val items = mutableListOf<KinItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KinViewHolder = KinViewHolder(parent,listener)


    override fun getItemCount(): Int= items.size


    override fun onBindViewHolder(holder: KinViewHolder, position: Int) =holder.bind(items[position])

    fun resetItems(ListItems: List<KinItem>) {
        items.clear()
        items.addAll(ListItems)
        notifyDataSetChanged()
    }
}