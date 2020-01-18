package app.ch.study.ui.main.presenter

import app.ch.study.core.BaseContract
import app.ch.study.data.remote.response.MovieModel

class MainContract {

    interface View : BaseContract.View {
        fun showMovieList(items: MutableList<MovieModel>)
        fun showErrorEmptyQuery()
        fun showEmptyResult()
    }

    interface Presenter : BaseContract.Presenter {
        fun searchMovie(name: String)
    }
}