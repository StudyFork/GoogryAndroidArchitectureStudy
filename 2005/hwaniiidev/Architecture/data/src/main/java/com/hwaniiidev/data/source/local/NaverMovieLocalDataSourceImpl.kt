package com.hwaniiidev.architecture.data.source.local

import android.content.Context
import com.hwaniiidev.data.model.Item
import com.hwaniiidev.data.source.local.NaverMovieLocalDataSource

class NaverMovieLocalDataSourceImpl(
    private val context: Context) : NaverMovieLocalDataSource {

    private var moviesDB: MoviesDB? = null

    override fun cachingMovies(query: String, movies: ArrayList<Item>) {
        moviesDB = MoviesDB.getInstance(context)

        val addMovies = Runnable {
            val cachedMovies = moviesDB?.moviesDao()?.getCachedData(query)

            //이미 저장되어 있는 영화 제거
            var iterator = movies.listIterator()
            while (iterator.hasNext()) {
                val inputMovie = iterator.next()
                if (cachedMovies != null) {
                    for (cashedData in cachedMovies) {
                        if (cashedData.title.equals(inputMovie.title)
                            && cashedData.director.equals(inputMovie.director)
                        ) {
                            iterator.remove()
                            break
                        }
                    }
                }
            }

            if (moviesDB != null) {
                //로컬에 저장
                for (it in movies) {
                    //검색어 맵핑
                    it.query = query
                    moviesDB?.moviesDao()?.insert(movies = it)
                }
            }
        }
        val addThread = Thread(addMovies)
        addThread.start()

    }

    override fun getCachedMovies(
        query: String,
        onCached: (movies: List<Item>?) -> Unit
    ) {
        moviesDB = MoviesDB.getInstance(context)

        val getMovies = Runnable {
            val cachedMovies = moviesDB?.moviesDao()?.getCachedData(query)

            onCached(cachedMovies)
        }
        val getThread = Thread(getMovies)
        getThread.start()
    }
}