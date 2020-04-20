package com.byiryu.remote.model.mapper

import com.byiryu.data.model.Item
import com.byiryu.data.model.Response
import com.byiryu.remote.model.MovieItem
import com.byiryu.remote.model.MovieResponse

interface Mapper{
    interface MovieMapper{
        fun remoteToData(movieResponse: MovieResponse): Response

        fun remoteToDataItem(movieItems: List<MovieItem>): List<Item>

        fun dataToRemote(response: Response): MovieResponse

        fun dataToRemoteItem(items: List<Item>) : List<MovieItem>
    }

    interface MusicMapper{

    }
}
