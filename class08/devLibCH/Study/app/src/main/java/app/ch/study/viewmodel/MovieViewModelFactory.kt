package app.ch.study.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.ch.study.data.db.dao.MovieDao
import app.ch.study.data.remote.api.WebApi

class MovieViewModelFactory(private val api: WebApi, private val movieDao: MovieDao) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(api, movieDao) as T
    }

}