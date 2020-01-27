package com.cnm.homework.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cnm.homework.R
import com.cnm.homework.data.model.NaverResponse
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {


    private val movieAdapter = MovieAdapter(::showMovieDetail)

    private val presenter: MainContract.Presenter by lazy { MainPresenter(this@MainActivity) }

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
            et_movie_search.hideKeyboard()
            val query = et_movie_search.text.toString()
            presenter.movieListSearch(query)
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
        presenter.disposableClear()
        super.onDestroy()
    }

    private fun beforeMovieListSearch() =
        runOnUiThread {
            movieAdapter.setItem(presenter.loadMovieList())
        }

    private fun showMovieDetail(item: NaverResponse.Item) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
        startActivity(intent)
    }

    override fun setItem(items: List<NaverResponse.Item>) {
        movieAdapter.setItem(items)
        rv_content.scrollToPosition(0)
    }

    override fun showProgress() {
        pb_loading.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pb_loading.visibility = View.GONE
    }

    override fun showErrorEmtpyResult() =
        Toast.makeText(this, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show()


    override fun showErrorEmptyQuery() =
        Toast.makeText(this, "제목을 입력해주세요", Toast.LENGTH_SHORT).show()


    override fun getContext(): Context = this@MainActivity

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}
