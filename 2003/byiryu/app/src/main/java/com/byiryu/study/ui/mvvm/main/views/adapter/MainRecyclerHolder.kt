package com.byiryu.study.ui.mvvm.main.views.adapter

import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.byiryu.study.BR
import com.byiryu.study.wigets.OnViewClickListener
import java.lang.Exception

class MainRecyclerHolder(
    itemView: View,
    lifecycleOwner: LifecycleOwner,
    private val onViewClickListener: OnViewClickListener
) : RecyclerView.ViewHolder(itemView) {

    var viewDataBinding : ViewDataBinding? = null

    init {
        try{
            viewDataBinding = DataBindingUtil.bind(itemView)
            viewDataBinding?.lifecycleOwner = lifecycleOwner
        }catch (e : Exception){
            Log.e("MainRecyclerHolder", "viewHolder Exception : $e")
        }

    }

    fun bind(item: Any){
        viewDataBinding?.run {
            setVariable(BR.movieItem, item)
            setVariable(BR.itemClickListener, onViewClickListener)
            executePendingBindings()
        }
    }
}