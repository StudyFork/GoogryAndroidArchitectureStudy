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
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.song2.myapplication.R
import com.song2.myapplication.adapter.MovieAdapter
import com.song2.myapplication.databinding.ActivityMainBinding
import com.song2.myapplication.source.MovieData

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), MainContract.View {

    private val imm: InputMethodManager by lazy { getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }
    private val movieAdapter by lazy { MovieAdapter() }
    private val presenter: MainContract.Presenter by lazy { MainPresenter(this) }

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setKeyboardFunc()
        setMovieRecyclerView()

        binding.btnMainActSearchBtn.setOnClickListener {

            if (binding.etMainActSearch.text.toString() != "") {
                movieAdapter.clearData()
                presenter.getMovie(binding.etMainActSearch.text.toString())
            }
        }
    }

    override fun showGetMovieSuccess(movieDataList: List<MovieData>) {

        imm.hideSoftInputFromWindow(binding.etMainActSearch.windowToken, 0)

        movieAdapter.addItem(movieDataList)
    }

    override fun showGetMovieFailure(e: Throwable) {
        Log.e("통신 실패", e.toString())
    }

    override fun setResultVisible() {
        binding.tvMainActMovieList.visibility = View.GONE
    }

    override fun setResultGone() {
        binding.tvMainActMovieList.visibility = View.VISIBLE
    }


    override fun getListCnt(): Int {
        return movieAdapter.itemCount
    }

    private fun setMovieRecyclerView() {

        binding.rvMainActMovieList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = movieAdapter
        }

        binding.rvMainActMovieList.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!binding.rvMainActMovieList.canScrollVertically(-1)) {
                    presenter.getMovie(binding.etMainActSearch.text.toString())
                }
            }
        })
    }

    private fun setKeyboardFunc() {

        binding.etMainActSearch.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        movieAdapter.clearData()
                        presenter.getMovie(binding.etMainActSearch.text.toString())
                    }
                    else ->
                        return false
                }
                return true
            }
        })
    }
}