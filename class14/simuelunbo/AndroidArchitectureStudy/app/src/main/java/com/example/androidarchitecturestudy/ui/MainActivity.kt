package com.example.androidarchitecturestudy.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.androidarchitecturestudy.R
import com.example.androidarchitecturestudy.data.local.NaverLocalDataSourceImpl
import com.example.androidarchitecturestudy.data.model.Movie
import com.example.androidarchitecturestudy.data.model.MovieData
import com.example.androidarchitecturestudy.data.remote.NaverRemoteDataSourceImpl
import com.example.androidarchitecturestudy.data.repository.NaverRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private val remoteMovieDataImpl = NaverRemoteDataSourceImpl()
    private val localMovieDataImpl = NaverLocalDataSourceImpl()
    private val repositoryMovieImpl = NaverRepositoryImpl(remoteMovieDataImpl, localMovieDataImpl)

    private val mainPresenter by lazy {
        MainPresenter(this, repositoryMovieImpl)
    }

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter { link ->
            mainPresenter.openMovieLink(link)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()

        btn_search.setOnClickListener {
            val searchText = et_search.text.toString()
            mainPresenter.requestMovieInfo(searchText)
        }
    }

    private fun initRecyclerView() {
        mainPresenter.requestLocalMovieData()
        rcv_result.adapter = movieAdapter
    }

    override fun hideKeyboard() {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(et_search.windowToken, 0)
    }

    override fun showLoadingBar() {
        progressBar.isVisible = true
    }

    override fun hideLoadingBar() {
        progressBar.isVisible = false
    }

    override fun showQueryEmpty() {
        Toast.makeText(this, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
    }

    override fun showResultEmpty(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
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

}