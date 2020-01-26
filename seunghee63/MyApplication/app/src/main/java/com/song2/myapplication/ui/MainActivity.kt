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
import com.song2.myapplication.source.MovieRepositoryImpl

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), MainContract.View {

    private val imm: InputMethodManager by lazy { getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }
    private val movieAdapter by lazy { MovieAdapter() }
    private val viewModel : MainViewModel by lazy { MainViewModel(MovieRepositoryImpl()) }

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.vm = viewModel

        setKeyboardFunc()
        setMovieRecyclerView()

        with(binding){
            btnMainActSearchBtn.setOnClickListener {
                if (etMainActSearch.text.toString() != "") {
                    movieAdapter.clearData()
                    viewModel.getMovie(etMainActSearch.text.toString())
                }
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

    private fun setMovieRecyclerView() {

        with(binding){
            rvMainActMovieList.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = movieAdapter
            }

            rvMainActMovieList.setOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (!rvMainActMovieList.canScrollVertically(-1)) {
                        viewModel.getMovie(etMainActSearch.text.toString())
                    }
                }
            })
        }


    }

    private fun setKeyboardFunc() {

        binding.etMainActSearch.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        movieAdapter.clearData()
                        viewModel.getMovie(binding.etMainActSearch.text.toString())
                    }
                    else ->
                        return false
                }
                return true
            }
        })
    }
}