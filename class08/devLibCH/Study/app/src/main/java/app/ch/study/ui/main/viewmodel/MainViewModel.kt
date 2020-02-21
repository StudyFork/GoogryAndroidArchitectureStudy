package app.ch.study.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    private val repository = NaverQueryRepositoryImpl(local, remote)

    val query = MutableLiveData<String>()
    val result = MutableLiveData<MutableList<MovieModel>>()
    val isEmpty = MutableLiveData<Boolean>()
    val showLoading = MutableLiveData<Boolean>()

    private val _showError = MutableLiveData<String>()
    val showError: LiveData<String> get() = _showError

    init {
        query.value = localDataManager.getQuery()
        searchMovie()
    }

    fun searchMovie() {
        val name = query.value

        if (name.isNullOrEmpty()) {
            _showError.value = KEY_QUERY_EMPTY
            return
        }

        showLoading.value = true

        repository.searchMovie(name)
            .doOnComplete {
                showLoading.value = false
            }
            .subscribe({ response ->
                val list = response.items
                isEmpty.value = list.isEmpty()
                result.value = list
            }, {
                val error = handleError(it)
                _showError.value = error
                showLoading.value = false
            })
            .addToDisposable()
    }

}