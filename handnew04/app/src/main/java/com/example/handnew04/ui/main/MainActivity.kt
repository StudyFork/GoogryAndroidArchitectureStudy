package com.example.handnew04.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.handnew04.R
import com.example.handnew04.adapter.MovieRecyclerAdapter
import com.example.handnew04.data.NaverMovieResponse
import com.example.handnew04.data.items
import com.example.handnew04.network.NetworkManager
import com.example.handnew04.ui.movie.MovieDetailActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainContract.View {
    lateinit var recyclerAdapter: MovieRecyclerAdapter
    val presenter: MainContract.Presenter by lazy {
        MainPresenter(
            this,
            NetworkManager(this@MainActivity.application)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initailize()
        setEventHandler()
    }


    private fun initailize() {
        recyclerAdapter = MovieRecyclerAdapter()

        recyclerAdapter.setItemClickListener(object :
            MovieRecyclerAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                //TODO 클릭 리스너 처리를 어떻게 해야 할지 잘 모르겠습니다.
                showMovieDetailActivity(position)
            }
        })

        rcv_movies.adapter = recyclerAdapter
    }

    private fun setEventHandler() {
        btn_searchButton.setOnClickListener {
            val inputText = et_searchBar.text.toString()
            presenter.serchMovie(inputText)
        }
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
        recyclerAdapter.setItemList(data.items as ArrayList<items>)
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
        pb_searchLoading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_searchLoading.visibility = View.GONE
    }
}
