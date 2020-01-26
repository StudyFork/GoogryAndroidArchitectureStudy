package com.example.architecture_project.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import com.example.architecture_project.R
import com.example.architecture_project.`object`.ObjectCollection.URL
import com.example.architecture_project.data.model.NaverApi
import com.example.architecture_project.databinding.ActivityMainBinding
import com.example.architecture_project.feature.movie.MovieAdapter
import com.example.architecture_project.feature.search.WebviewActivity

class MainActivity : AppCompatActivity() {

    private lateinit var movieAdapter: MovieAdapter
    lateinit var binding: ActivityMainBinding
    val vm: MainViewModel by lazy { MainViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.mainActivity = this@MainActivity
        binding.vm = vm

        movieAdapter = MovieAdapter(object : MovieAdapter.MovieViewHolder.ItemClickListener {
            override fun onItemClick(position: Int) {
                val goWebView = Intent(this@MainActivity, WebviewActivity::class.java)
                goWebView.putExtra(URL, movieAdapter.getMovieLink(position))
                startActivity(goWebView)
            }
        })
        binding.rvMovie.adapter = movieAdapter
        ObservableData()

    }

    fun callMovie() {   //수정완료
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

    private fun showResult(data: NaverApi) {
        movieAdapter.setMovieItemList(data.item)
    }

    private fun showDataNum(num: Int) {
        Toast.makeText(this, "총 " + num + "개가 검색되었습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun ObservableData() {
        vm.isEmptyMovieData.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                showNoResult()
            }
        })

        vm.isEmptyKeyword.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                showNotExistKeyword()
            }
        })

        vm.hasWrongChar.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                showNotAvailableKeyword()
            }
        })
        vm.movieDataNum.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                showDataNum(vm.movieDataNum.get()!!)
            }
        })

    }
}
