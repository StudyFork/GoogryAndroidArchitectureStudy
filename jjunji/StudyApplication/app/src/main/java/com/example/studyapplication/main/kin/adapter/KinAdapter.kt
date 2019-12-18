package com.example.studyapplication.main.kin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studyapplication.R
import com.example.studyapplication.vo.KinList

class KinAdapter : RecyclerView.Adapter<KinHolder>() {
    private val arrKinInfo : MutableList<KinList.KinInfo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KinHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view : View = inflater.inflate(R.layout.item_kin, parent, false)

        return KinHolder(view)
    }

    override fun onBindViewHolder(holder: KinHolder, position: Int) {
        arrKinInfo[position].let {
            with(holder) {
                tvTitle.text = it.title
                tvDescription.text = it.description
            }
        }
    }

    override fun getItemCount(): Int {
        return arrKinInfo.size
    }

    fun resetItem(items: Array<KinList.KinInfo>) {
        arrKinInfo.clear()
        arrKinInfo.addAll(items)
        notifyDataSetChanged()
    }
}