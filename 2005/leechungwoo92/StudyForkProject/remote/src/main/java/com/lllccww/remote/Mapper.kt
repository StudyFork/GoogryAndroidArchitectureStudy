package com.lllccww.remote

import com.lllccww.data.model.MovieItem


interface Mapper{
    interface MovieMapper{
        fun remoteToData(items: ArrayList<com.lllccww.remote.MovieItem>): List<MovieItem>
    }

}