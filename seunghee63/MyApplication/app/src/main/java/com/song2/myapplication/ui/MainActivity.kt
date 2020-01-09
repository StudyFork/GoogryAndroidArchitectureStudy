package com.song2.myapplication.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.song2.myapplication.R
import com.song2.myapplication.adapter.MovieAdapter
import com.song2.myapplication.source.MovieData
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var imm: InputMethodManager
    private val movieAdapter by lazy { MovieAdapter() }
    private val presenter: MainContract.Presenter by lazy { MainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setKeyboardFunc()
        setMovieRecyclerView()

        btn_main_act_search_btn.setOnClickListener {
            presenter.getMovie(btn_main_act_search_btn.text.toString())
        }
    }

    override fun showGetMovieSuccess(movieDataList: List<MovieData>) {

        if (movieDataList.count() == 0) {
            tv_main_act_movie_list.visibility = View.VISIBLE
        } else {
            tv_main_act_movie_list.visibility = View.GONE
        }

        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(et_main_act_search.windowToken, 0)

        movieAdapter.setMovieList(movieDataList)
    }

    override fun showGetMovieFailure(e: Throwable) {
        Log.e("통신 실패", e.toString())
    }

    //무한스크롤
    override fun showGetMoreDataSuccess(movieDataList: List<MovieData>) {
        movieAdapter.addItem(movieDataList)
    }

    private fun setMovieRecyclerView() {

        rv_main_act_movie_list.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movieAdapter
        }

        rv_main_act_movie_list.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!rv_main_act_movie_list.canScrollVertically(1)) {
                    presenter.getMoreMovie(et_main_act_search.text.toString())
                }
            }
        })
    }

    private fun setKeyboardFunc() {

        et_main_act_search.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        presenter.getMovie(et_main_act_search.text.toString())
                    }
                    else ->
                        return false
                }
                return true
            }
        })
    }
}