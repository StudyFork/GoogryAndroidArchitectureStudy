package com.example.studyapplication.main.kin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapplication.R
import com.example.studyapplication.data.model.KinList
import kotlinx.android.synthetic.main.item_kin.view.*

class KinAdapter(var arrKinInfo : Array<KinList.KinInfo>) : RecyclerView.Adapter<KinHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KinHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view : View = inflater.inflate(R.layout.item_kin, parent, false)

        return KinHolder(view)
    }

    override fun onBindViewHolder(holder: KinHolder, position: Int) {
        holder.itemView.tvTitle.text = arrKinInfo[position].title
        holder.itemView.tvDescription.text = arrKinInfo[position].description
    }

    override fun getItemCount(): Int {
        return arrKinInfo.size
    }

}