package com.example.seonoh.seonohapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.seonoh.seonohapp.model.CurrentPriceInfoModel

class KrwAdapter(data: ArrayList<CurrentPriceInfoModel>) : RecyclerView.Adapter<KrwItemViewHolder>(){

    lateinit var mView : View
    val mData = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KrwItemViewHolder {

        mView = LayoutInflater.from(parent.context).inflate(R.layout.krw_item,parent,false)
        return KrwItemViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: KrwItemViewHolder, position: Int) {
        holder.bind(mData,position)
    }
}

