package com.architecture.androidarchitecturestudy.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.architecture.androidarchitecturestudy.R
import com.architecture.androidarchitecturestudy.adapter.MovieAdapter
import com.architecture.androidarchitecturestudy.data.model.Movie
import com.architecture.androidarchitecturestudy.data.remote.MovieRemoteDataSource
import com.architecture.androidarchitecturestudy.data.remote.MovieRemoteDataSourceImpl
import com.architecture.androidarchitecturestudy.data.repository.MovieRepository
import com.architecture.androidarchitecturestudy.data.repository.MovieRepositoryImpl
import com.architecture.androidarchitecturestudy.webservice.ApiClient
import com.architecture.androidarchitecturestudy.webservice.NetworkService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var networkService: NetworkService
    private lateinit var movieRepository: MovieRepository
    private lateinit var movieRemoteDataSource: MovieRemoteDataSource
    private lateinit var movieAdapter: MovieAdapter
    private val presenter by lazy {
        networkService = ApiClient.NETWORK_SERVICE
        movieRemoteDataSource = MovieRemoteDataSourceImpl(networkService)
        val repositoryMovieDataImpl = MovieRepositoryImpl(movieRemoteDataSource)
        MainPresenter(this, repositoryMovieDataImpl)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        initRepository()

        btn_main_search.setOnClickListener {
            if (et_main_search.text.isEmpty()) {
                Toast.makeText(this, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                searchMovie(et_main_search.text.toString())
                removeKeyboard()
            }
        }
    }

    private fun initRepository() {
        networkService = ApiClient.NETWORK_SERVICE
        movieRemoteDataSource = MovieRemoteDataSourceImpl(networkService)
        movieRepository = MovieRepositoryImpl(movieRemoteDataSource)
    }

    private fun initRecyclerView() {
        movieAdapter = MovieAdapter()
        rv_main_movie.adapter = movieAdapter
    }

    private fun searchMovie(keyword: String) {
        movieRepository.getMovieData(
            keyword,
            30,
            onSuccess = { movieAdapter.setItemList(it.items as ArrayList<Movie>) },
            onFailure = { Log.e("Api is fail", it.toString()) })
    }

    private fun removeKeyboard() =
        (this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            et_main_search.windowToken, 0
        )

    override fun updateMovieRecycler(items: List<Movie>) {
        movieAdapter.setItemList(items)
    }

    override fun failMovieGet(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun findMovie(keyword: String) {
        presenter.findMovie(keyword)
    }

    override fun queryNone() {
        Toast.makeText(this, getString(R.string.query_none), Toast.LENGTH_SHORT)
            .show()
    }

    override fun resultNone() {
        Toast.makeText(this, getString(R.string.result_none), Toast.LENGTH_SHORT)
            .show()
    }
}
