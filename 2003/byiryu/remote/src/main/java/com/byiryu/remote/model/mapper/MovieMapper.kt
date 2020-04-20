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
        val items = ArrayList<Item>()
        movieItems.forEach{ item ->
            items.add(Item(
                item.actor,
                item.director,
                item.image,
                item.link,
                item.pubDate,
                item.subtitle,
                item.title,
                item.userRating
            ))
        }
        return items.toList()
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
        val _items = ArrayList<MovieItem>()
        items.forEach{ item ->
            _items.add(MovieItem(
                item.actor,
                item.director,
                item.image,
                item.link,
                item.pubDate,
                item.subtitle,
                item.title,
                item.userRating
            ))
        }
        return _items.toList()
    }





}