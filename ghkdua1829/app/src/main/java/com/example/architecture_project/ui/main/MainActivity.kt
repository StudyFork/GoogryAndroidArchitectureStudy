package com.example.architecture_project.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.architecture_project.R
import com.example.architecture_project.`object`.ObjectCollection.URL
import com.example.architecture_project.data.model.Movie
import com.example.architecture_project.databinding.ActivityMainBinding
import com.example.architecture_project.feature.movie.MovieAdapter
import com.example.architecture_project.feature.search.WebviewActivity

class MainActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieAdapter
    lateinit var binding: ActivityMainBinding
    lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        vm = ViewModelProviders.of(this@MainActivity)[MainViewModel::class.java]

        binding.mainActivity = this@MainActivity
        binding.vm = vm
        binding.lifecycleOwner = this@MainActivity

        movieAdapter = MovieAdapter(object : MovieAdapter.MovieViewHolder.ItemClickListener {
            override fun onItemClick(position: Int) {
                val goWebView = Intent(this@MainActivity, WebviewActivity::class.java)
                goWebView.putExtra(URL, movieAdapter.getMovieLink(position))
                startActivity(goWebView)
            }
        })
        binding.rvMovie.adapter = movieAdapter
        addObservableData()

    }

    fun callMovie() {
        vm.getMovieData(binding.etSearch.text.toString())
    }

    private fun showNoResult() {
        Toast.makeText(this, "검색결과가 없습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun showNotExistKeyword() {
        Toast.makeText(this, "검색어를 입력하세요.", Toast.LENGTH_SHORT).show()
    }

    private fun showNotAvailableKeyword() {
        Toast.makeText(this, "유효하지 않는 키워드입니다.", Toast.LENGTH_SHORT).show()
    }

    private fun showDataNum(num: Int) {
        Toast.makeText(this, "총 " + num + "개가 검색되었습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun addObservableData() {
        vm.isEmptyMovieData.observe(this, Observer<Boolean> { showNoResult() })

        vm.isEmptyKeyword.observe(this, Observer<Boolean> { showNotExistKeyword() })

        vm.hasWrongChar.observe(this, Observer<Boolean> { showNotAvailableKeyword() })
        vm.movieDataNum.observe(this, Observer<Int> { showDataNum(vm.movieDataNum.value!!) })
        vm.movieData.observe(this, Observer<List<Movie>> {
            if (it.isNotEmpty()) {
                movieAdapter.setMovieItemList(it)
            }
        })

    }
}
