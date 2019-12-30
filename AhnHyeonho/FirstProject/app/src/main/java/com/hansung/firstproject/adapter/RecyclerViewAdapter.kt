package com.hansung.firstproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hansung.firstproject.R
import com.hansung.firstproject.data.MovieModel

class RecyclerViewAdapter<T> : RecyclerView.Adapter<MovieHolder>() {
    private val items: ArrayList<T> = arrayListOf()

   fun addItems(_item: ArrayList<T>){
        items.addAll(_item)
    }

    fun clearItems(){
        items.clear()
    }

    //영화 아이템의 갯수 가져오기
    override fun getItemCount() = items.size

    // ViewHolder를 생성하고 View를 붙여주는 method
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieHolder(view)
    }

    //재활용 되는 View가 호출하여 실행되는 메소드, 뷰 홀더를 전달하고 Adapter는 position 의 데이터를 결합
    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        (items[position] as MovieModel).run {
            holder.bindItems(this)
        }
    }
}