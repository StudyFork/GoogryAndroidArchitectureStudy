package app.ch.study.data.local.source

import app.ch.study.core.App
import app.ch.study.data.local.LocalDataManager
import app.ch.study.data.remote.response.MovieModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NaverQueryLocalDataSourceImpl : NaverQueryLocalDataSource {

    override fun searchMovie(): MutableList<MovieModel> {
        val jsonList = (LocalDataManager.getInstance(App.context)?.searchMovieList()) ?: ""
        return if (jsonList.isEmpty())
            mutableListOf()
        else {
            val gson = Gson()
            val listType = object : TypeToken<MutableList<MovieModel>>() {}.type
            gson.fromJson<MutableList<MovieModel>>(jsonList, listType)
        }
    }

    override fun saveMovieList(movieList: MutableList<MovieModel>) {
        val gson = Gson()
        val jsonList = gson.toJson(movieList)
        LocalDataManager.getInstance(App.context)?.saveMovieList(jsonList)
    }

}