package m.woong.architecturestudy

import m.woong.architecturestudy.data.source.remote.model.MovieResponse

interface MovieContract {

    interface View {
        fun showMovieList(movieResponse: MovieResponse)
        fun showErrorEmptyQuery()
        fun showErrorResponseError(errorMsg: String)
    }

    interface Presenter {
        fun requestMovieList(query: String)
    }
}