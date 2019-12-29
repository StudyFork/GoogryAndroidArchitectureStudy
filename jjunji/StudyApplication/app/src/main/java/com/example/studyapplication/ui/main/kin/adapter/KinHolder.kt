package com.example.studyapplication.ui.main.kin.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_kin.view.*

class KinHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvTitle: TextView = itemView.tvTitle
    val tvDescription : TextView = itemView.tvDescription
}