package app.ch.study.data.remote.response

import app.ch.study.data.db.entitiy.MovieModel

class MovieResponse(
    var items: List<MovieModel> = ArrayList()
)