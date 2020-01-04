package com.cnm.homework.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cnm.homework.R
import com.cnm.homework.network.NetworkHelper
import com.cnm.homework.network.model.NaverResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val movieAdapter = MovieAdapter(::showMovieDetail)
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_content.adapter = movieAdapter

        bt_movie_search.setOnClickListener {
            if (et_movie_search.text.toString() != "") {
                val query = et_movie_search.text.toString()
                movieListSearch(query)
            } else {
                Toast.makeText(this, "제목을 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        }
        et_movie_search.setOnEditorActionListener { _, i, _ ->
            when (i) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    bt_movie_search.performClick()
                }
            }
            true
        }
    }


    private fun movieListSearch(query: String) {
        showProgress()
        disposable.add(NetworkHelper.naverApi.getNaverMovie(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                hideProgress()
                movieAdapter.setItem(it.items)
            })
    }

    private fun showMovieDetail(item: NaverResponse.Item) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
        startActivity(intent)
    }

    private fun showProgress() {
        pb_loading.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        pb_loading.visibility = View.GONE
    }

}
