package m.woong.architecturestudy

import m.woong.architecturestudy.data.repository.MovieRepository

@Deprecated("Change Architecture to MVVM")
class MoviePresenter(
    private val view: MovieContract.View,
    private val repository: MovieRepository
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
                view.showErrorResponseMsg(it)
            })
    }

    private fun showErrorEmptyQuery() {
        view.showErrorEmptyQuery()
    }
}