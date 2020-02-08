package app.ch.study.ui.main.presenter

import androidx.databinding.ObservableField
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

    var query = ObservableField<String>()
    var isVisible = ObservableField<Boolean>()

    init {
        query.set(localDataManager.getQuery())
        searchMovie()
    }

    override fun searchMovie() {
        val name = query.get()?:""

        if (name.isEmpty()) {
            view.showEmptyResult()
            return
        }

        repository.searchMovie(name)
            .doOnSubscribe {
                view.showLoading()
            }
            .doOnTerminate {
                view.hideLoading()
            }
            .subscribe({ response ->
                val list = response.items

                if (list.isEmpty()) {
                    isVisible.set(true)
                    view.showEmptyResult()
                    return@subscribe
                }

                isVisible.set(false)
                view.showMovieList(list)
            }, {
                val error = handleError(it)
                view.showError(error)
            })
            .addToDisposable()
    }
}