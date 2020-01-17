package com.example.architecture_project.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.architecture_project.R
import com.example.architecture_project.`object`.ObjectCollection.URL
import com.example.architecture_project.data.model.NaverApi
import com.example.architecture_project.databinding.ActivityMainBinding
import com.example.architecture_project.feature.movie.MovieAdapter
import com.example.architecture_project.feature.search.WebviewActivity

class MainActivity : AppCompatActivity(),
    MainContract.View {

    private lateinit var movieAdapter: MovieAdapter
    val presenter: MainContract.Presenter = MainPresenter(this)
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.mainActivity = this@MainActivity
        movieAdapter = MovieAdapter(object : MovieAdapter.MovieViewHolder.ItemClickListener {
            override fun onItemClick(position: Int) {
                val goWebView = Intent(this@MainActivity, WebviewActivity::class.java)
                goWebView.putExtra(URL, movieAdapter.getMovieLink(position))
                startActivity(goWebView)
            }
        })
        binding.rvMovie.adapter = movieAdapter


    }

    fun callMovie() {   //수정완료
        presenter.getMovieData(binding.etSearch.text.toString())
    }

    override fun showNoResult() {
        Toast.makeText(this, "검색결과가 없습니다.", Toast.LENGTH_SHORT).show()
    }

    override fun showNotExistKeyword() {
        Toast.makeText(this, "검색어를 입력하세요.", Toast.LENGTH_SHORT).show()
    }

    override fun showNotAvailableKeyword() {
        Toast.makeText(this, "유효하지 않는 키워드입니다.", Toast.LENGTH_SHORT).show()
    }

    override fun showResult(data: NaverApi) {
        movieAdapter.setMovieItemList(data.item)
    }

    override fun showDataNum(num: Int) {
        Toast.makeText(this, "총 " + num + "개가 검색되었습니다.", Toast.LENGTH_SHORT).show()
    }
}
