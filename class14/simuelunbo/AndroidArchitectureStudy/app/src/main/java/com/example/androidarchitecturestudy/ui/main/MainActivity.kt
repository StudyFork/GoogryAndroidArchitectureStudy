package com.example.androidarchitecturestudy.ui.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.androidarchitecturestudy.R
import com.example.androidarchitecturestudy.data.local.NaverLocalDataSourceImpl
import com.example.androidarchitecturestudy.data.model.Movie
import com.example.androidarchitecturestudy.data.model.MovieData
import com.example.androidarchitecturestudy.data.remote.NaverRemoteDataSourceImpl
import com.example.androidarchitecturestudy.data.repository.NaverRepositoryImpl
import com.example.androidarchitecturestudy.databinding.ActivityMainBinding
import com.example.androidarchitecturestudy.ui.MovieAdapter
import com.example.androidarchitecturestudy.ui.TitleFragmentDialog
import com.example.androidarchitecturestudy.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    BaseActivity<MainContract.Presenter, ActivityMainBinding>(R.layout.activity_main),
    MainContract.View {
    private val mainPresenter by lazy {
        val remoteMovieDataImpl = NaverRemoteDataSourceImpl()
        val localMovieDataImpl = NaverLocalDataSourceImpl()
        val repositoryMovieImpl = NaverRepositoryImpl(remoteMovieDataImpl, localMovieDataImpl)
        MainPresenter(this, repositoryMovieImpl)
    }

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter { link ->
            mainPresenter.openMovieLink(link)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this
        initRecyclerView()
    }

    fun searchMovies() {
        val searchText = et_search.text.toString()
        mainPresenter.requestMovieInfo(searchText)
    }

    fun showHistory(){
        TitleFragmentDialog().show(supportFragmentManager,"title_history")
    }

    private fun initRecyclerView() {
        mainPresenter.requestLocalMovieData()
        binding.rcvResult.adapter = movieAdapter
    }

    override fun hideKeyboard() {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(et_search.windowToken, 0)
    }


    override fun showQueryEmpty() {
        Toast.makeText(this, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
    }


    override fun showResult(movieData: MovieData) {
        movieAdapter.setMovieList(movieData.items as ArrayList<Movie>)
        mainPresenter.saveMovieData(movieData.items)
    }

    override fun getMovieData(movie: List<Movie>) {
        movieAdapter.setMovieList(movie)
    }

    override fun openMovieLink(link: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
    }

    override fun showLoadingBar() {
        binding.progressBar.isVisible = true
    }

    override fun hideLoadingBar() {
        binding.progressBar.isVisible = false
    }

}