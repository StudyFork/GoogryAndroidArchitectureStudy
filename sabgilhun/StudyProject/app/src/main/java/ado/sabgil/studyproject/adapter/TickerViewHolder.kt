package ado.sabgil.studyproject.adapter

import ado.sabgil.studyproject.databinding.ItemTickerBinding
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

class TickerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding: ItemTickerBinding = requireNotNull(DataBindingUtil.bind(itemView))

}