package com.example.handnew04.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.handnew04.R
import com.example.handnew04.adapter.MovieRecyclerAdapter
import com.example.handnew04.data.NaverMovieResponse
import com.example.handnew04.data.MovieData
import com.example.handnew04.databinding.ActivityMainBinding
import com.example.handnew04.network.NetworkManager
import com.example.handnew04.ui.movie.MovieDetailActivity


class MainActivity : AppCompatActivity(), MainContract.View {
    lateinit var recyclerAdapter: MovieRecyclerAdapter
    lateinit var binding: ActivityMainBinding

    val presenter: MainContract.Presenter by lazy {
        MainPresenter(
            this,
            NetworkManager(this@MainActivity.application)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        binding.activity = this@MainActivity

        initailize()
    }


    private fun initailize() {
        recyclerAdapter = MovieRecyclerAdapter()

        recyclerAdapter.setItemClickListener(object :
            MovieRecyclerAdapter.ItemClickListener {
            override fun onClick(position: Int) {
                showMovieDetailActivity(position)
            }
        })

        binding.rcvMovies.adapter = recyclerAdapter
    }

    fun searchButtonClick() {
        val inputText = binding.etSearchBar.text.toString()
        presenter.serchMovie(inputText)
    }

    override fun showEmptyResult() {
        Toast.makeText(this@MainActivity, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show()
    }

    override fun showInputLengthZero() {
        Toast.makeText(this@MainActivity, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
    }

    override fun showNotConnectedNetwork() {
        Toast.makeText(this@MainActivity, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show()
    }

    override fun showSuccessSearchMovie(data: NaverMovieResponse) {
        recyclerAdapter.setItemList(data.items as ArrayList<MovieData>)
    }

    override fun showMovieDetailActivity(position: Int) {
        val nextIntent = Intent(this@MainActivity, MovieDetailActivity::class.java)
        nextIntent.putExtra(
            getString(R.string.movieLink),
            recyclerAdapter.getMovieLink(position)
        )
        startActivity(nextIntent)
    }

    override fun showFailSearchMovie(message: String?) {
        Toast.makeText(this@MainActivity, "검색에 실패하였습니다. MSG : $message", Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        binding.isLoadingVisible = true
    }

    override fun hideLoading() {
        binding.isLoadingVisible = false
    }
}
