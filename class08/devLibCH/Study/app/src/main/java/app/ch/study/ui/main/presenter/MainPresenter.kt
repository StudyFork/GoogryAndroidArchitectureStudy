package app.ch.study.ui.main.presenter

import app.ch.study.core.BasePresenter
import app.ch.study.data.local.source.NaverQueryLocalDataSourceImpl
import app.ch.study.data.remote.api.WebApiTask
import app.ch.study.data.remote.source.NaverQueryRemoteDataSourceImpl
import app.ch.study.data.repository.NaverQueryRepositoryImple

class MainPresenter(private val view: MainContract.View) : BasePresenter(), MainContract.Presenter {

    private lateinit var repository: NaverQueryRepositoryImple

    override fun searchMovie(name: String) {
        if (name.isEmpty()) {
            view.showEmptyResult()
            return
        }

        val local = NaverQueryLocalDataSourceImpl()
        val remote = NaverQueryRemoteDataSourceImpl(WebApiTask.getInstance())
        repository = NaverQueryRepositoryImple(local, remote)

        val search = repository.searchMovie(name)

        addDisposable(search
            .doOnSubscribe {
                view.showLoading()
            }
            .doOnTerminate {
                view.hideLoading()
            }
            .subscribe({ response ->
                val list = response.items

                if(list.isEmpty()) {
                    view.showEmptyResult()
                    return@subscribe
                }

                view.showMovieList(list)
            }, {
                val error = handleError(it)
                view.showError(error)
            }))
    }
}