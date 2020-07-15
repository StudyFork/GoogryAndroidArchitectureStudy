package m.woong.architecturestudy

import m.woong.architecturestudy.data.repository.MovieRepositoryImpl

class MoviePresenterImpl(
    private val view: MovieContract.View,
    private val repository: MovieRepositoryImpl
) : MoviePresenter {

    override fun requestMovieList(query: String) {

        if (query.isEmpty()) {
            showErrorEmptyQuery()
        } else {
            getRecentMovie(query)
        }
    }

    override fun getRecentMovie(query: String) {
        repository.getRecentMovie(query,
            success = {
                view.showMovieList(it)
            }, failure = {
                view.showErrorResponseMsg(it)
            })
    }

    override fun showErrorEmptyQuery() {
        view.showErrorEmptyQuery()
    }
}