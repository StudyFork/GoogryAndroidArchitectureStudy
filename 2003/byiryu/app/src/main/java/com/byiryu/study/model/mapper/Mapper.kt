package com.byiryu.study.model.mapper

import com.byiryu.data.model.Item
import com.byiryu.study.model.Movie

interface Mapper {
    interface MovieMapper{
        fun dataToView(items: List<Item>) : List<Movie>

        fun viewToData(movies: List<Movie>) : List<Item>
    }
}