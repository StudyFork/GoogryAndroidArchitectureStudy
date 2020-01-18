package com.cnm.homework.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cnm.homework.R
import com.cnm.homework.applySchedulers
import com.cnm.homework.data.model.NaverResponse
import com.cnm.homework.data.repository.NaverQueryRepositoryImpl
import com.cnm.homework.data.source.local.NaverQueryLocalDataSourceImpl
import com.cnm.homework.data.source.local.db.LocalDao
import com.cnm.homework.data.source.local.db.LocalDatabase
import com.cnm.homework.data.source.remote.NaverQueryRemoteDataSourceImpl
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val movieAdapter = MovieAdapter(::showMovieDetail)
    private val disposable = CompositeDisposable()
    private val localDao: LocalDao by lazy {
        val db = LocalDatabase.getInstance(this)!!
        db.localDao()
    }
//    private val presenter : MainContract.Presenter by lazy { MainPresenter(this@MainActivity) }

    private val naverQueryRepositoryImpl: NaverQueryRepositoryImpl by lazy {
        NaverQueryRepositoryImpl(
            NaverQueryRemoteDataSourceImpl(),
            NaverQueryLocalDataSourceImpl(localDao)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_content.adapter = movieAdapter
        if (movieAdapter.movieItems.isEmpty()) {
            val r = Runnable { beforeMovieListSearch() }
            val thread = Thread(r)
            thread.start()
        }
        bt_movie_search.setOnClickListener {
// 버튼 클릭시 presenter의 함수 호출
            et_movie_search.hideKeyboard()
            if (et_movie_search.text.toString().isNotEmpty()) {
                val query = et_movie_search.text.toString()
                movieListSearch(query)
            } else {
                toastShow("제목을 입력해주세요")
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

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }


    private fun beforeMovieListSearch() {
        val repoItem = naverQueryRepositoryImpl.loadLocal()
        Log.e("log","$repoItem")
        runOnUiThread {
        movieAdapter.setItem(repoItem)
        }
    }

    private fun movieListSearch(query: String) {


        disposable.add(naverQueryRepositoryImpl.getNaverMovie(query)
            .applySchedulers()
            .doOnSubscribe {
                showProgress()
            }
            .doAfterTerminate {
                hideProgress()
            }
            .subscribe({
                if (it.total != 0) {
                    movieAdapter.setItem(it.items)
                    rv_content.scrollToPosition(0)
                } else {
                    toastShow("검색 결과가 없습니다.")
                }
            }, {
                it.printStackTrace()
            })


        )
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

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun toastShow(content: String) =
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
}
