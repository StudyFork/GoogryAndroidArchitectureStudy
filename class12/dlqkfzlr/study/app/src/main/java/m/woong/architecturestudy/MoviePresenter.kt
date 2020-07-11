package m.woong.architecturestudy

import m.woong.architecturestudy.data.repository.NaverRepositoryImpl

class MoviePresenter(
    private val view: MovieContract.View,
    private val repository: NaverRepositoryImpl
) : MovieContract.Presenter {

    override fun requestMovieList(query: String) {

        if (query.isEmpty()) {
            showErrorEmptyQuery()
        } else {
            getRecentMovie(query)
        }
    }

    private fun getRecentMovie(query: String) {
        repository.getRecentMovie(query,
            success = {
                view.showMovieList(it)
            }, failure = {
                view.showErrorResponseError(it.toString())
            })
    }

    private fun showErrorEmptyQuery() {
        view.showErrorEmptyQuery()
    }
}