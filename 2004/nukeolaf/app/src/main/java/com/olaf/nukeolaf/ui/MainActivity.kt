package com.olaf.nukeolaf.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.olaf.nukeolaf.R
import com.olaf.nukeolaf.data.local.MovieLocalDataSourceImpl
import com.olaf.nukeolaf.data.model.MovieItem
import com.olaf.nukeolaf.data.remote.MovieRemoteDataSourceImpl
import com.olaf.nukeolaf.data.repository.MovieRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private val movieAdapter = MovieAdapter()
    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movie_rv.adapter = movieAdapter

        presenter = MainPresenter(
            MovieRepositoryImpl(
                MovieLocalDataSourceImpl(applicationContext),
                MovieRemoteDataSourceImpl()
            ), this
        ).apply { loadMovies() }

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.searchMovie(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun showMovies(movies: List<MovieItem>) {
        movieAdapter.setMovies(movies)
    }

    override fun showEmptySearchWord() {
        makeToast("검색어를 입력해주세요")
    }

    override fun showNoResultForSearchWord(query: String) {
        makeToast("${query}에 대한 검색결과가 없습니다")
    }

    override fun showServerError() {
        makeToast("서버 에러 : 서버에 문제가 있습니다")
    }

    override fun showNetworkError() {
        makeToast("네트워크 에러 : 인터넷 연결을 확인해 주세요")
    }

    private fun makeToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }
}
