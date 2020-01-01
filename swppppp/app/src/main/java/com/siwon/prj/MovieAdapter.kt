package com.siwon.prj

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.siwon.prj.model.Movie

// 클릭리스너를 nallable하게 받아
class MovieAdapter(val clickListener: (String) -> Unit) : RecyclerView.Adapter<MovieHolder>() {
    private val _items = ArrayList<Movie>()

    fun setItems(items: ArrayList<Movie>) {
        _items.clear()
        _items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder
            = MovieHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))

    override fun getItemCount(): Int = _items.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int)
            = holder.bind(_items[position], clickListener)

}

// Generic 사용해서 base어댑터 만듦 -> 상속받아서 구현해보기!!!!