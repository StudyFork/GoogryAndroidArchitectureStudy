package com.example.kotlinapplication.ui.view.page.kin

import android.view.ViewGroup
import com.example.kotlinapplication.data.model.KinItem
import com.example.kotlinapplication.ui.base.BaseAdapter

class KinAdapter(private val listener: (KinItem) -> Unit) : BaseAdapter<KinItem, KinViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KinViewHolder =
        KinViewHolder(parent, listener)

    override fun onBindViewHolder(holder: KinViewHolder, position: Int) =
        holder.bind(items[position])

}