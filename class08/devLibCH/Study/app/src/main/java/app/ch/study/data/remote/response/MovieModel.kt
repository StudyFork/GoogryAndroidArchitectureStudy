package app.ch.study.data.remote.response

data class MovieModel(
    var title: String,
    var link: String,
    var image: String,
    var pubDate: String,
    var director: String,
    var actor: String,
    var userRating: Float
)