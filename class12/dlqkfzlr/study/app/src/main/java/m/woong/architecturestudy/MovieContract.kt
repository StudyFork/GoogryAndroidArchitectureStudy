package m.woong.architecturestudy

import m.woong.architecturestudy.data.source.remote.model.MovieResponse

@Deprecated("Change Architecture to MVVM")
interface MovieContract {

    interface View {
        fun showMovieList(movieResponse: MovieResponse)
        fun showErrorEmptyQuery()
        fun showErrorResponseMsg(t: Throwable)
    }

    interface Presenter {
        fun requestMovieList(query: String)
    }
}