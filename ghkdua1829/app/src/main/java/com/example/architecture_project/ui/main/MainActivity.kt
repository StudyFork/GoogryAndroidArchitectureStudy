package com.example.architecture_project.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.architecture_project.R
import com.example.architecture_project.`object`.ObjectCollection.URL
import com.example.architecture_project.data.model.NaverApi
import com.example.architecture_project.data.repository.NaverRepository
import com.example.architecture_project.feature.movie.MovieAdapter
import com.example.architecture_project.feature.search.WebviewActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    MainContract.View {

    private lateinit var movieRecyclerView: RecyclerView   //수정완료
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var naverRepository: NaverRepository
    val presenter: MainContract.Presenter =
        MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        naverRepository = NaverRepository()
        movieRecyclerView = findViewById(R.id.rv_movie)
        movieAdapter = MovieAdapter(object : MovieAdapter.MovieViewHolder.ItemClickListener {
            override fun onItemClick(position: Int) {
                val goWebView = Intent(this@MainActivity, WebviewActivity::class.java)
                goWebView.putExtra(URL, movieAdapter.getMovieLink(position))
                startActivity(goWebView)
            }
        })
        movieRecyclerView.adapter = movieAdapter

        btn_search.setOnClickListener {
            callMovie(et_search.text.toString())
        }
    }

    private fun callMovie(keyword: String) {   //수정완료
        presenter.getMovieData(keyword)
    }

    override fun noShowResult() {
        Toast.makeText(this, "검색결과가 없습니다.", Toast.LENGTH_SHORT).show()
    }

    override fun noKeyword() {
        Toast.makeText(this, "검색어를 입력하세요.", Toast.LENGTH_SHORT).show()
    }

    override fun notAvailableKeyword() {
        Toast.makeText(this, "유효하지 않는 키워드입니다.", Toast.LENGTH_SHORT).show()
    }

    override fun showResult(data: NaverApi) {
        movieAdapter.setMovieItemList(data.item)
    }

}
