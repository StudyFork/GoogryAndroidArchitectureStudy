package com.example.hw2_project.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.hw2_project.R
import com.example.hw2_project.data.api.NaverMovieData
import com.example.hw2_project.data.repository.MovieRepositoryImpl
import com.example.hw2_project.databinding.ActivityMainBinding
import com.example.hw2_project.recentSearch.RecentSearchActivity

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var binding: ActivityMainBinding

    private val mainAdapter = MainRecyclerViewAdapter()

    private val movieRepositoryImp = MovieRepositoryImpl()

    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        binding.mainActivity = this@MainActivity

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = mainAdapter

        mainPresenter = MainPresenter(this, movieRepositoryImp)
        intentCheck()
    }

    fun clickSearchBtn() {
        hideKeyboard()
        mainPresenter.requestMovieListToRepo(binding.etMovieName.text.toString())
    }

    fun showRecentSearchMovie() {
        val intent = Intent(this, RecentSearchActivity::class.java)
        startActivity(intent)
    }

    override fun showErrorEmptyQuery() {
        Toast.makeText(this, "영화제목을 입력해주세요.", Toast.LENGTH_SHORT).show()
    }

    override fun showMovieList(movieList: NaverMovieData.NaverMovieResponse) {
        runOnUiThread {
            mainAdapter.updateMovieList(movieList.items)
        }
    }

    override fun showErrorRespondMsg(t: Throwable) {
        Log.e("showErrorRespondMsg", t.stackTraceToString())
    }

    private fun hideKeyboard() {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.etMovieName.windowToken, 0)
    }

    private fun intentCheck() {
        if (intent.hasExtra("query")) {
            intent.getStringExtra("query")?.let {
                mainPresenter.requestMovieListToRepo(it)
            }
        }
    }

}