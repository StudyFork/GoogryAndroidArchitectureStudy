package com.cnm.homework.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.cnm.homework.R
import com.cnm.homework.adapter.MovieAdapter
import com.cnm.homework.data.model.NaverResponse
import com.cnm.homework.data.source.local.db.LocalDao
import com.cnm.homework.data.source.local.db.LocalDatabase
import com.cnm.homework.databinding.ActivityMainBinding
import com.cnm.homework.extension.hideKeyboard

class MainActivity : AppCompatActivity(), MainContract.View {

    private val movieAdapter = MovieAdapter(::showMovieDetail)

    private val localDao: LocalDao by lazy {
        val db = LocalDatabase.getInstance(this)!!
        db.localDao()
    }
    private val presenter: MainContract.Presenter by lazy {
        MainPresenter(
            this@MainActivity,
            localDao
        )
    }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.rvContent.adapter = movieAdapter
        binding.activity = this@MainActivity

        if (movieAdapter.movieItems.isEmpty()) {
            val r = Runnable { beforeMovieListSearch() }
            val thread = Thread(r)
            thread.start()
        }

    }

    fun buttonClick() {
        binding.etMovieSearch.hideKeyboard()
        val query = binding.etMovieSearch.text.toString()
        presenter.movieListSearch(query)
    }

    override fun onDestroy() {
        presenter.disposableClear()
        super.onDestroy()
    }

    private fun beforeMovieListSearch() {
        val repoItem = presenter.loadMovieList()
        runOnUiThread {
            movieAdapter.setItem(repoItem)
        }
    }

    private fun showMovieDetail(item: NaverResponse.Item) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
        startActivity(intent)
    }

    override fun setItem(items: List<NaverResponse.Item>) {
        movieAdapter.setItem(items)
        binding.rvContent.scrollToPosition(0)
    }

    override fun showProgress() {
        binding.isPbVisible = true
    }

    override fun hideProgress() {
        binding.isPbVisible = false
    }

    override fun showEmptyLayout() {
        with(binding) {
            isRvVisible = false
            isFlVisible = true
        }
    }

    override fun hideEmptyLayout() {
        with(binding) {
            isRvVisible = true
            isFlVisible = false
        }
    }

    override fun showErrorEmptyResult() =
        Toast.makeText(this, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show()

    override fun showErrorEmptyQuery() =
        Toast.makeText(this, "제목을 입력해주세요", Toast.LENGTH_SHORT).show()

}
