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
import com.example.archstudy.data.source.local.MovieData

class MainActivity : AppCompatActivity(), MainInterface.View {

    // View
    private lateinit var edtQuery: EditText
    private lateinit var btnSearch: Button
    private lateinit var rvMovieList: RecyclerView
    private lateinit var rvMovieAdapter: MovieListAdapter

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

    override fun showErrorMessage(errrorMessage: Throwable) {
        showToast(errrorMessage.toString())
    }

    override fun showDataList(dataList: MutableList<MovieData>) {
        rvMovieAdapter.setAllData(dataList)
        insertLocalData(query,dataList)
    }

    override fun initPresenter() {
        presenter = MainPresenter(applicationContext)
        presenter.bindView(this)
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

    // 사용자가 입력한 검색어로 네이버 영화 검색 API 에서 데이터 얻어오기
    private fun requestRemoteData(query: String) {
        presenter.getRemoteDataByQuery(query)
    }

    private fun requestLocalData() {
        Log.d("init", "requestLocalData()")
        val localQuery = presenter.getLocalQuery()
        presenter.getLocalData(localQuery)
    }

    private fun insertLocalData(query: String, data: MutableList<MovieData>) {
        presenter.insertData(query,data)
    }

        Log.d("init","requestLocalData.query : $query")
        try {
            // 최근 검색한 query 를 PK로 하여 LocalDB에서 데이터 비동기로 얻어오기
            var requestResult =
                repositoryImpl
                .RequestLocalDataAsync(query)
                .execute()
                .get()
            Log.d("init","query : $query")
            Log.d("init","requestResult : $requestResult")

            // 최근 검색 순대로 정렬
            requestResult = requestResult.asReversed()
            rvMovieAdapter.setAllData(requestResult) // Local Data 세팅

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

//    private fun insertLocalData(query: String, data: MutableList<MovieData>) {
//        repositoryImpl.InsertLocalDataAsync(query, data).execute()
//    }



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