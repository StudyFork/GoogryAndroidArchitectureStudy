package com.byiryu.remote.model.mapper

import com.byiryu.data.model.Item
import com.byiryu.data.model.Response
import com.byiryu.remote.model.MovieItem
import com.byiryu.remote.model.MovieResponse

object MovieMapper: Mapper.MovieMapper{
    override fun remoteToData(movieResponse: MovieResponse): Response{
        return Response(
            movieResponse.total,
            movieResponse.start,
            movieResponse.display,
            movieResponse.lastBuildDate,
            remoteToDataItem(movieResponse.items)
        )
    }

    override fun remoteToDataItem(movieItems: List<MovieItem>): List<Item>{
        return movieItems.map {
            Item(
                it.actor,
                it.director,
                it.image,
                it.link,
                it.pubDate,
                it.subtitle,
                it.title,
                it.userRating
            )
        }
    }


    override fun dataToRemote(response: Response) : MovieResponse{
        return MovieResponse(
            response.total,
            response.start,
            response.display,
            response.lastBuildDate,
            dataToRemoteItem(response.items)
        )
    }
    override fun dataToRemoteItem(items: List<Item>): List<MovieItem>{
        return items.map {
            MovieItem(
                it.actor,
                it.director,
                it.image,
                it.link,
                it.pubDate,
                it.subtitle,
                it.title,
                it.userRating
            )
        }.toList()
    }





}