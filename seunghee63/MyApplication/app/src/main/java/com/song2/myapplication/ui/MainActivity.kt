package com.song2.myapplication.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.song2.myapplication.R
import com.song2.myapplication.adapter.MovieAdapter
import com.song2.myapplication.source.MovieRepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var imm : InputMethodManager
    private val movieRepository by lazy { MovieRepositoryImpl() }
    private val movieAdapter by lazy { MovieAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setKeyboardFunc()

        setMovieRecyclerView()

        btn_main_act_search_btn.setOnClickListener {
            getMovieData(et_main_act_search.text.toString())
        }
    }

    private fun setMovieRecyclerView() {

        rv_main_act_movie_list.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movieAdapter
        }

    }

    private fun getMovieData(keyword: String) {

        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(et_main_act_search.windowToken, 0)

        movieRepository.getMovieData(keyword, 30,
            onSuccess = { movieAdapter.setMovieList(it) },
            onFailure = { Log.e("실패", it.toString()) }
        )
    }

    private fun setKeyboardFunc() {

        et_main_act_search.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        getMovieData(et_main_act_search.text.toString())
                    }
                    else ->
                        return false
                }
                return true
            }
        })
    }
}