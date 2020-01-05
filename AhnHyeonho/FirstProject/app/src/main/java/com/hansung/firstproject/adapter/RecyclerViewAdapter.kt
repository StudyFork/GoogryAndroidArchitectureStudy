package com.hansung.firstproject.adapter

import android.os.Build
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.hansung.firstproject.R
import com.hansung.firstproject.data.MovieModel

class RecyclerViewAdapter : RecyclerView.Adapter<MovieHolder>() {
    private val items: ArrayList<MovieModel> = arrayListOf()

    fun addItems(_item: ArrayList<MovieModel>) {
        clearItems()
        items.addAll(_item)
    }

    private fun clearItems() {
        items.clear()
    }

    //영화 아이템의 갯수 가져오기
    override fun getItemCount() = items.size

    // ViewHolder를 생성하고 View를 붙여주는 method
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder =
        object : MovieHolder(R.layout.movie_item, parent) {}

    //재활용 되는 View가 호출하여 실행되는 메소드, 뷰 홀더를 전달하고 Adapter는 position 의 데이터를 결합
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bindItems(items[position])
    }
}