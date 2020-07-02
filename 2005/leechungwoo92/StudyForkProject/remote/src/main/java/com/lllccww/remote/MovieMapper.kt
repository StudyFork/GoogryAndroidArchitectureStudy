package com.lllccww.remote

object MovieMapper : Mapper.MovieMapper {
    override fun remoteToData(items: ArrayList<MovieItem>): List<com.lllccww.data.model.MovieItem> {
        return items.toList().map {
            com.lllccww.data.model.MovieItem(
                it.title,
                it.link,
                it.image,
                it.subtitle,
                it.pubDate,
                it.director,
                it.userRating
            )
        }
    }

}