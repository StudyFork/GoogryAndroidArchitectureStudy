package com.example.study.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.study.R
import com.example.study.ui.adapter.MovieAdapter
import com.example.study.data.datasource.remote.network.NaverApiService
import com.example.study.data.model.Movie
import com.example.study.data.model.NaverSearchResponse
import com.example.study.data.repository.NaverSearchRepository
import com.example.study.data.repository.NaverSearchRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    private val movieAdapter = MovieAdapter()
    private val naverSearchRepository: NaverSearchRepository = NaverSearchRepositoryImpl()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_movie_list.adapter = movieAdapter

        btn_search.setOnClickListener {
            getMovieList(et_movie_search.text.toString())
        }

        movieAdapter.setOnItemClickListener { movie ->
            var intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.MOVIE_URL, movie.link.toString())

            this.startActivity(intent)
        }
    }

    private fun getMovieList(query: String) {
        compositeDisposable.add(naverSearchRepository.getMovies(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                showProgress()
            }
            .doAfterTerminate {
                hideProgress()
            }
            .subscribe({
                it?.let {
                    if (it.items.isNotEmpty()) {
                        movieAdapter.setItem(it.items)
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            R.string.error_message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }, {
                it.printStackTrace()
            })
        )

    }

    private fun showProgress() {
        pb_loading.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        pb_loading.visibility = View.GONE
    }
}




