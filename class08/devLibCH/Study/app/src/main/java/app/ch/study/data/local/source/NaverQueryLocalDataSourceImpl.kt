package app.ch.study.data.local.source

import app.ch.study.data.local.LocalDataManager
import app.ch.study.data.remote.response.MovieModel
import app.ch.study.data.remote.response.MovieResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NaverQueryLocalDataSourceImpl(private val localDataManager: LocalDataManager) :
    NaverQueryLocalDataSource {

    override fun searchMovie(): MovieResponse {
        val jsonList = localDataManager.searchMovieList()
        val response = MovieResponse()

        if (jsonList.isNotEmpty()) {
            val gson = Gson()
            val listType = object : TypeToken<MutableList<MovieModel>>() {}.type
            val list = gson.fromJson<MutableList<MovieModel>>(jsonList, listType)

            response.items = list
        }

        return response
    }

    override fun saveMovieList(movieList: MutableList<MovieModel>) {
        val gson = Gson()
        val jsonList = gson.toJson(movieList)
        localDataManager.saveMovieList(jsonList)
    }

    override fun saveSearchQuery(query: String) =
        localDataManager.saveSearchQuery(query)

    override fun getQuery(): String =
        localDataManager.getQuery()

}