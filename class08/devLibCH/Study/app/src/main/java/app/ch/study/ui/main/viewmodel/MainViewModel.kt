package app.ch.study.ui.main.viewmodel

import androidx.databinding.ObservableField
import app.ch.study.core.BaseViewModel
import app.ch.study.data.common.KEY_QUERY_EMPTY
import app.ch.study.data.local.LocalDataManager
import app.ch.study.data.local.source.NaverQueryLocalDataSourceImpl
import app.ch.study.data.remote.api.WebApiTask
import app.ch.study.data.remote.response.MovieModel
import app.ch.study.data.remote.source.NaverQueryRemoteDataSourceImpl
import app.ch.study.data.repository.NaverQueryRepositoryImpl
import app.ch.study.util.handleError

class MainViewModel(localDataManager: LocalDataManager): BaseViewModel() {

    private val local = NaverQueryLocalDataSourceImpl(localDataManager)
    private val remote = NaverQueryRemoteDataSourceImpl(WebApiTask.getInstance())
    private var repository = NaverQueryRepositoryImpl(local, remote)

    var query = ObservableField<String>()
    var result = ObservableField<MutableList<MovieModel>>()
    var isEmpty = ObservableField<Boolean>()
    var showLoading = ObservableField<Boolean>()
    var showError = ObservableField<String>()

    init {
        query.set(localDataManager.getQuery())
        searchMovie()
    }

    fun searchMovie() {
        val name = query.get()?:""

        if (name.isEmpty()) {
            showError.set(KEY_QUERY_EMPTY)
            return
        }

        showLoading.set(true)

        repository.searchMovie(name)
            .doOnComplete {
                showLoading.set(false)
            }
            .subscribe({ response ->
                val list = response.items
                isEmpty.set(list.isEmpty())
                result.set(list)
            }, {
                val error = handleError(it)
                showError.set(error)
                showLoading.set(false)
            })
            .addToDisposable()
    }

}