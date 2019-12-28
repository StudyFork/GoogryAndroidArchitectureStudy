package com.ironelder.androidarchitecture.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ironelder.androidarchitecture.R
import com.ironelder.androidarchitecture.data.ResultItem
import com.ironelder.androidarchitecture.databinding.ItemCustomItemViewBinding


@SuppressLint("ViewConstructor")
class CustomItemViewImpl(context: Context?) :
    ConstraintLayout(context),
    CustomItemView<ResultItem> {

    private val mCustomItemViewBinding: ItemCustomItemViewBinding = DataBindingUtil.inflate(
        (context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater),
        R.layout.item_custom_item_view,
        this,
        true
    )

    init {
        layoutParams = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }

    override fun setData(item: ResultItem) {
        mCustomItemViewBinding.item = item
    }
}