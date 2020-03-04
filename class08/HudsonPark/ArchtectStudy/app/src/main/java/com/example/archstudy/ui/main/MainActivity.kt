package com.example.archstudy.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.archstudy.R
import com.example.archstudy.data.source.local.AppDatabase
import com.example.archstudy.data.source.local.MovieData
import com.example.archstudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainContract.View {

    // View
    private lateinit var edtQuery: EditText
    private lateinit var btnSearch: Button
    private lateinit var rvMovieList: RecyclerView
    private lateinit var rvMovieAdapter: MovieListAdapter
    // DataBinding
    private lateinit var binding: ActivityMainBinding
    // variables
    private var query = ""
    // Presenter
    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Binding View
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initRecyclerView() // 리사이클러 뷰 초기화
        initPresenter() // 프레젠터 초기화
        initData() // 데이터 초기화
        initEvent() // 이벤트 처리
    }

    override fun showMessage(msg: String) {
        showToast(msg)
    }

    override fun showErrorMessage(error: Throwable) {
        showToast(error.message.toString())
    }

    override fun showDataList(dataList: MutableList<MovieData>) {
        rvMovieAdapter.setAllData(dataList)
    }

    override fun initPresenter() {

        val localMovieDao = AppDatabase.getInstance(this)?.localMovieDao()
        val searchWordDao = AppDatabase.getInstance(this)?.searchWordDao()

        if (localMovieDao != null && searchWordDao != null) {
            presenter = MainPresenter(this, localMovieDao, searchWordDao)
        }
    }

    private fun initData() {
        presenter.initData()
    }

    private fun initEvent() {

        with(binding){
            btnSearch.setOnClickListener {
                hideKeyboard()
                disableButton()
                // 검색 버튼 클릭 시
                query = edtQuery.text.toString()
                Log.d("query", "query : $query")
                presenter.getData(query)
                activateButton()
            }
        }
    }

    private fun initRecyclerView() {

        rvMovieAdapter = MovieListAdapter(object :
            MovieListAdapter.ItemClickListener {
            override fun onItemClick(url: String) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            }
        })
        binding.rvMovieList.adapter = rvMovieAdapter
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun activateButton() {
        binding.btnSearch.isEnabled = true
    }

    private fun disableButton() {
        binding.btnSearch.isEnabled = false
    }

    private fun hideKeyboard() {
        val inputManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(binding.edtQuery.windowToken, 0)
    }
}