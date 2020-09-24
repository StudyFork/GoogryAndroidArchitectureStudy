package com.example.dkarch.presentation.main

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dkarch.R
import com.example.dkarch.data.entity.Movie
import com.example.dkarch.databinding.ActivityMainBinding
import com.example.dkarch.domain.api.usecase.GetMovieListUseCase
import com.example.dkarch.domain.globalconsts.Consts
import com.example.dkarch.domain.repository.NaverMovieRepository
import com.example.dkarch.domain.repositoryImpl.HttpClientRepositoryImpl
import com.example.dkarch.domain.repositoryImpl.NaverMovieRepositoryImpl
import com.example.dkarch.domain.repositoryImpl.RetrofitRepositoryImpl
import com.example.dkarch.presentation.MovieAdapter
import com.example.dkarch.presentation.QueryHistoryFragment
import com.example.dkarch.presentation.QueryHistoryFragment.Companion.HISTORY_DIALOG_TAG
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import retrofit2.HttpException

class MainActivity : AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()
    private val naverMovieRepository: NaverMovieRepository =
        NaverMovieRepositoryImpl(GetMovieListUseCase(RetrofitRepositoryImpl(HttpClientRepositoryImpl())))

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter

    private var movieList = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpDataBinding()
        initView()
    }

    private fun setUpDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
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
            getMovieList(query)
        } else {
            Toast.makeText(this, "영화제목을 입력하세요!", Toast.LENGTH_LONG).show()
        }
    }

    fun historyButtonClicked() {
        val queryHistoryList = naverMovieRepository.getQueryList()
        val bundle = Bundle()
        bundle.putStringArrayList(Consts.FRAGMENT_QUERY_LIST, ArrayList(queryHistoryList))
        QueryHistoryFragment().apply {
            arguments = bundle
            show(supportFragmentManager, HISTORY_DIALOG_TAG)
        }
    }

    private fun handleError(throwable: Throwable) {
        if (throwable is HttpException) {
            val statusCode = throwable.code()
            if (statusCode == 400) {

            }
        }
    }

    fun getMovieList(query: String) {
        binding.title.setText(query)
        naverMovieRepository.getMovies(query)
            .subscribe({ movieResponse ->
                movieResponse.body()?.let {
                    movieAdapter.submitList(it.items)
                }
            }, {
                handleError(it)
            }).addTo(compositeDisposable)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

}
