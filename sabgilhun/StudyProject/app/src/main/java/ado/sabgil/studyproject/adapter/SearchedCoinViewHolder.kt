package ado.sabgil.studyproject.adapter

import ado.sabgil.studyproject.databinding.ItemSearchedTickerBinding
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

class SearchedCoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val binding: ItemSearchedTickerBinding = requireNotNull(DataBindingUtil.bind(itemView))

}