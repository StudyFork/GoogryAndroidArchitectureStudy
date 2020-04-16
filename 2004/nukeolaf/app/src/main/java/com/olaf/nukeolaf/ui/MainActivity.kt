package com.olaf.nukeolaf.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.olaf.nukeolaf.R
import com.olaf.nukeolaf.data.local.MovieLocalDataSourceImpl
import com.olaf.nukeolaf.data.model.MovieItem
import com.olaf.nukeolaf.data.model.MovieResponse
import com.olaf.nukeolaf.data.remote.MovieRemoteDataSourceImpl
import com.olaf.nukeolaf.data.repository.MovieRepository
import com.olaf.nukeolaf.data.repository.MovieRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val movieAdapter = MovieAdapter()
    private lateinit var movieLocalDataSource: MovieLocalDataSourceImpl
    private lateinit var movieRemoteDataSource: MovieRemoteDataSourceImpl
    private lateinit var movieRepository: MovieRepositoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movie_rv.adapter = movieAdapter

        movieLocalDataSource = MovieLocalDataSourceImpl(applicationContext)
        movieRemoteDataSource = MovieRemoteDataSourceImpl()
        movieRepository = MovieRepositoryImpl(movieLocalDataSource, movieRemoteDataSource)

        loadMovies()

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchMovie(query)
                } else {
                    makeToast("검색어를 입력해주세요")
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun searchMovie(query: String) {
        movieRepository.searchMovies(
            query,
            object : MovieRepository.LoadMoviesCallback {
                override fun onMoviesLoaded(movieResponse: MovieResponse) {
                    if (movieResponse.items.isNotEmpty()) {
                        showMovies(movieResponse.items)
                    } else {
                        makeToast("${query}에 대한 검색결과가 없습니다")
                    }
                }

                override fun onResponseError(message: String) {
                    makeToast("서버 에러 : 서버에 문제가 있습니다")
                }

                override fun onFailure(t: Throwable) {
                    makeToast("네트워크 에러 : 인터넷 연결을 확인해 주세요")
                }
            })
    }

    private fun loadMovies() {
        val movieList = movieRepository.getMovies()
        if (movieList != null && movieList.items.isNotEmpty()) {
            showMovies(movieList.items)
        }
    }

    private fun showMovies(movies: List<MovieItem>) {
        movieAdapter.setMovies(movies)
    }

    private fun makeToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }
}
