package com.siwon.prj.common.adapter

import android.view.View
import com.siwon.prj.common.base.BaseViewHolder
import com.siwon.prj.databinding.ListItemBinding
import com.siwon.prj.common.model.Movie

// TODO 시간 되면 제네릭 타입으로 데이터 받아 보세요!
class MovieHolder (private val binding: ListItemBinding, private val action: ((Int) -> Unit)? = null) : BaseViewHolder<Movie>(binding.root), View.OnClickListener {

    init {
        binding.root.setOnClickListener(this)
    }
    override fun onBind(item: Movie) {
        with(binding) {
            data = item
        }
    }

    override fun onClick(v: View?) {
        action?.let { it(adapterPosition) }
    }
}