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
import androidx.recyclerview.widget.RecyclerView
import com.example.archstudy.R
import com.example.archstudy.data.source.local.AppDatabase
import com.example.archstudy.data.source.local.MovieData

class MainActivity : AppCompatActivity(), MainInterface.View {

    // View
    private lateinit var edtQuery: EditText
    private lateinit var btnSearch: Button
    private lateinit var rvMovieList: RecyclerView
    private lateinit var rvMovieAdapter: MovieListAdapter
    // variables
    private var query = ""
    // Presenter
    private lateinit var presenter: MainInterface.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView() // 뷰 초기화
        initPresenter() // 프레젠터 초기화
        initData() // 데이터 초기화
        initEvent() // 이벤트 처리
    }

    override fun showMessage(msg: String) {
        showToast(msg)
    }

    override fun showErrorMessage(error: Throwable) {
        showToast(error.toString())
    }

    override fun showDataList(dataList: MutableList<MovieData>) {
        rvMovieAdapter.setAllData(dataList)
    }

    override fun initPresenter() {

        val localMovieDao = AppDatabase.getInstance(this)?.localMovieDao()
        val searchWordDao = AppDatabase.getInstance(this)?.searchWordDao()


        if (localMovieDao != null && searchWordDao != null) {

            presenter = MainPresenter(localMovieDao, searchWordDao)
            presenter.bindView(this)

        }
    }

    private fun initData() {
        requestLocalData()
    }

    private fun initEvent() {
        Log.d("init", "initEvent()")
        btnSearch.setOnClickListener {
            hideKeyboard()
            disableButton()
            // 검색 버튼 클릭 시
            query = edtQuery.text.toString()
            requestRemoteData(query)
            activateButton()
        }
    }

    private fun initView() {
        Log.d("init", "initView()")
        edtQuery = findViewById(R.id.edtQuery)
        btnSearch = findViewById(R.id.btnSearch)
        rvMovieList = findViewById(R.id.rvMovieList)

        rvMovieAdapter = MovieListAdapter(object :
            MovieListAdapter.ItemClickListener {
            override fun onItemClick(url: String) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            }
        })
        rvMovieList.adapter = rvMovieAdapter
    }

    // 사용자가 입력한 검색어로 네이버 영화 검색 API에서 데이터 얻어오기
    private fun requestRemoteData(query: String) {
        presenter.getRemoteDataByQuery(query)
    }

    private fun requestLocalData() {
        Log.d("init", "requestLocalData()")

        val localQuery = presenter.getLocalQuery()
        presenter.getLocalData(localQuery)

    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun activateButton() {
        btnSearch.isEnabled = true
    }

    private fun disableButton() {
        btnSearch.isEnabled = false
    }

    private fun hideKeyboard() {
        val inputManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(edtQuery.windowToken, 0)
    }
}