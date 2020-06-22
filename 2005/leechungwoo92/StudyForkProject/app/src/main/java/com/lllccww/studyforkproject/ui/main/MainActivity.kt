package com.lllccww.studyforkproject.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lllccww.studyforkproject.R
import com.lllccww.studyforkproject.data.repository.NaverMovieRepositoryImpl
import com.lllccww.studyforkproject.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var start = 1
    private var total = 0
    private var display = 0
    private lateinit var movieListAdapter: MovieListAdapter


    private lateinit var binding: ActivityMainBinding
    private val naverMovieRepositoryImpl = NaverMovieRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val viewModelProvider = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(naverMovieRepositoryImpl) as T
            }
        })
        val mainViewModel = viewModelProvider[MainViewModel::class.java]


        binding.vm = mainViewModel
        binding.lifecycleOwner = this

        movieListAdapter = MovieListAdapter()
        binding.rvMovieList.adapter = movieListAdapter


        mainViewModel.toastMessage.observe(this@MainActivity, Observer {
            showToastMsg(mainViewModel.toastMessage.value!!)
        })

        mainViewModel.hideKeyboard.observe(this@MainActivity, Observer {
            hideKeyboard()
        })

        rv_movie_list.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as? LinearLayoutManager)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = movieListAdapter.itemCount - 1

                if (lastVisibleItemPosition == itemTotalCount) {
                    Log.d("fail : ", "스크롤 최하단")
                    if (start < total) {
                        //requestSearchMovie(start + 10)
                    } else {
                        showToastMsg("마지막 페이지입니다.")
                    }

                }
            }
        })

    }


    fun showToastMsg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    //키보드 숨기기
    private fun hideKeyboard() {
        val view = this.currentFocus

        if (view != null) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


}
