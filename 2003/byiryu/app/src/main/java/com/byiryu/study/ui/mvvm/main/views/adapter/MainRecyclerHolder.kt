package com.byiryu.study.ui.mvvm.main.views.adapter

import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

class MainRecyclerHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    var viewDataBinding : ViewDataBinding? = null

    init {
        try{
            viewDataBinding = DataBindingUtil.bind(itemView)
        }catch (e : Exception){
            Log.e("MainRecyclerHolder", "viewHolder Exception : $e")
        }

    }
}