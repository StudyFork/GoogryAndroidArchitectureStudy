package com.example.dkarch.presentation.main

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dkarch.R
import com.example.dkarch.data.entity.Movie
import com.example.dkarch.databinding.ActivityMainBinding
import com.example.dkarch.domain.api.usecase.GetMovieListUseCase
import com.example.dkarch.domain.globalconsts.Consts
import com.example.dkarch.domain.repositoryImpl.HttpClientRepositoryImpl
import com.example.dkarch.domain.repositoryImpl.NaverMovieRepositoryImpl
import com.example.dkarch.domain.repositoryImpl.RetrofitRepositoryImpl
import com.example.dkarch.presentation.queryHistory.QueryHistoryFragment
import com.example.dkarch.presentation.queryHistory.QueryHistoryFragment.Companion.HISTORY_DIALOG_TAG
import com.example.dkarch.presentation.base.BaseActivity

class MainActivity :
    BaseActivity<MainContract.Presenter, ActivityMainBinding>(R.layout.activity_main),
    MainContract.View {

    override val presenter: MainContract.Presenter by lazy {
        MainPresenter(
            this,
            NaverMovieRepositoryImpl(
                GetMovieListUseCase(
                    RetrofitRepositoryImpl(HttpClientRepositoryImpl())
                )
            )
        )
    }

    private lateinit var movieAdapter: MovieAdapter
    private var movieList = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpDataBinding()
        initView()
    }

    private fun setUpDataBinding() {
        binding.activity = this
    }

    @SuppressLint("CheckResult")
    private fun initView() {
        movieAdapter = MovieAdapter(movieList, movieItemClicked)
        binding.rcView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = movieAdapter
        }
    }

    private val movieItemClicked = { link: String ->
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
    }

    @SuppressLint("CheckResult")
    fun searchButtonClicked() {
        val query = binding.title.text.toString()

        if (query.isNotEmpty()) {
            binding.title.setText(query)
            presenter.getMovieList(query)
        } else {
            showEmptyMessage()
        }
    }

    fun historyButtonClicked() {
        val queryHistoryList = presenter.getQueryList()
        val bundle = Bundle()
        bundle.putStringArrayList(Consts.FRAGMENT_QUERY_LIST, ArrayList(queryHistoryList))
        QueryHistoryFragment().apply {
            arguments = bundle
            show(supportFragmentManager, HISTORY_DIALOG_TAG)
        }
    }

    override fun showMovieList(movieList: ArrayList<Movie>) {
        movieAdapter.submitList(movieList)
    }

    override fun showEmptyMessage() {
        Toast.makeText(this, "영화제목을 입력하세요!", Toast.LENGTH_LONG).show()
    }

}
