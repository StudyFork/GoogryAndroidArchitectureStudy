package com.byiryu.local.model.mapper

import com.byiryu.data.model.Item
import com.byiryu.local.model.MovieItem

interface Mapper{
    interface MovieMapper{
        fun dataToLocal(items: List<Item>) : List<MovieItem>

        fun localToData(movieItems: List<MovieItem>) : List<Item>
    }

    interface MusicMapper{

    }
}