package app.ch.study.ui.main.presenter

import app.ch.study.core.BasePresenter
import app.ch.study.data.local.LocalDataManager
import app.ch.study.data.local.source.NaverQueryLocalDataSourceImpl
import app.ch.study.data.remote.api.WebApiTask
import app.ch.study.data.remote.source.NaverQueryRemoteDataSourceImpl
import app.ch.study.data.repository.NaverQueryRepositoryImpl

class MainPresenter(
    private val view: MainContract.View,
    localDataManager: LocalDataManager
) : BasePresenter(), MainContract.Presenter {

    private val local = NaverQueryLocalDataSourceImpl(localDataManager)
    private val remote = NaverQueryRemoteDataSourceImpl(WebApiTask.getInstance())
    private var repository = NaverQueryRepositoryImpl(local, remote)

    override fun searchMovie(name: String) {
        if (name.isEmpty()) {
            view.showEmptyResult()
            return
        }

        val search = repository.searchMovie(name)

        search
            .doOnSubscribe {
                view.showLoading()
            }
            .doOnTerminate {
                view.hideLoading()
            }
            .subscribe({ response ->
                val list = response.items

                if (list.isEmpty()) {
                    view.showEmptyResult()
                    return@subscribe
                }

                view.showMovieList(list)
            }, {
                val error = handleError(it)
                view.showError(error)
            })
            .addTo(compositeDisposable)
    }
}