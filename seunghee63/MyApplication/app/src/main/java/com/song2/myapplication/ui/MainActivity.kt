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
import com.song2.myapplication.source.MovieRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var imm: InputMethodManager
    private val movieRepository by lazy { MovieRepositoryImpl() }
    private val movieAdapter by lazy { MovieAdapter() }

    private lateinit var presenter : MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)

        setKeyboardFunc()
        setMovieRecyclerView()

        btn_main_act_search_btn.setOnClickListener {
            presenter.getMovie(btn_main_act_search_btn.text.toString())
        }
    }

    override fun showGetMovieFailure(e : Throwable) {
        Log.e("통신 실패",e.toString())
    }

    override fun showMovieNotExist(cnt : Int) {
        if (cnt == 0) {
            tv_main_act_movie_list.visibility = View.VISIBLE
        } else {
            tv_main_act_movie_list.visibility = View.GONE
        }
    }

    override fun showGetMovieSuccess(movieDataList: List<MovieData>) {
        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(et_main_act_search.windowToken, 0)

        movieAdapter.setMovieList(movieDataList)
    }

    override fun showGetMoreData(movieDataList: List<MovieData>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setMovieRecyclerView() {

        rv_main_act_movie_list.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movieAdapter
        }

        rv_main_act_movie_list.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!rv_main_act_movie_list.canScrollVertically(1)) {
                    addMovieData(et_main_act_search.text.toString())
                }
            }
        })

    }

    private fun addMovieData(keyword: String) {
        //TODO : 이전 데이터에 이어서 추가로 데이터를 받아오는 방법에 대한 기능 구현
        movieRepository.getMovieData(keyword, 20,
            onSuccess = { movieAdapter.addItem(it) },
            onFailure = { Log.e("실패", it.toString()) }
        )
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