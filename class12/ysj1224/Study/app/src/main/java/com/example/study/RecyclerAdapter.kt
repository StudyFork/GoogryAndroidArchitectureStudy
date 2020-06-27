package com.example.study

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {
    class MyViewHolder(myView: View) : RecyclerView.ViewHolder(myView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item, parent, false)
        return MyViewHolder(textView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder
    }

    override fun getItemCount() {
        
    }
}