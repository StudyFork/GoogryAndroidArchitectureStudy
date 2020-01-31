package com.example.archstudy.ui.main

import android.content.Context
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
import com.example.archstudy.data.repository.NaverQueryRepositoryImpl
import com.example.archstudy.data.source.local.AppDatabase
import com.example.archstudy.data.source.local.MovieData
import com.example.archstudy.data.source.local.NaverQueryLocalDataSourceImpl
import com.example.archstudy.data.source.remote.NaverQueryRemoteDataSourceImpl

class MainActivity : AppCompatActivity(), MainInterface.View {

    // View
    private lateinit var edtQuery: EditText
    private lateinit var btnSearch: Button
    private lateinit var rvMovieList: RecyclerView
    private lateinit var rvMovieAdapter: MovieListAdapter
    // Data
    private lateinit var repositoryImpl: NaverQueryRepositoryImpl
    private lateinit var localData: NaverQueryLocalDataSourceImpl
    private lateinit var remoteData: NaverQueryRemoteDataSourceImpl
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

    override fun getApplicationContext(): Context {
        return applicationContext
    }

    override fun showMessage(msg: String) {
        showToast(msg)
    }

    override fun showErrorMessage(msg: Throwable) {
        showToast(msg.toString())
    }

    override fun showDataList(dataList: MutableList<MovieData>) {
        rvMovieAdapter.setAllData(dataList)
    }

    override fun initPresenter() {
        presenter = MainPresenter()
    }

    private fun initData() {
        Log.d("init", "initData()")
        val localMovieDao = AppDatabase.getInstance(this)?.localMovieDao()
        val searchWordDao = AppDatabase.getInstance(this)?.searchWordDao()

        val query = presenter.getLocalQuery()

        localData = NaverQueryLocalDataSourceImpl(localMovieDao, searchWordDao)
        remoteData = NaverQueryRemoteDataSourceImpl()
        repositoryImpl = NaverQueryRepositoryImpl(localData, remoteData)

        var history =
            repositoryImpl
                .RequestLocalQueryAsync()
                .execute()
                .get()

        if (history != null) {
            requestLocalData(history)
        }
    }

    private fun initEvent() {
        Log.d("init", "initEvent()")
        btnSearch.setOnClickListener {
            hideKeyboard()
            disableButton()
            // 검색 버튼 클릭 시
            query = edtQuery.text.toString()
            if (query.isNullOrEmpty()) {
                showToast("검색어를 다시 입력해주세요.")
            } else {
                showToast("요청하신 관련 영화 : $query")
                requestRemoteData(query)
            }
            activateButton()
        }
    }

    private fun initView() {
        Log.d("init", "initView()")
        edtQuery = findViewById(R.id.edtQuery)
        btnSearch = findViewById(R.id.btnSearch)
        rvMovieList = findViewById(R.id.rvMovieList)
        // Presenter 초기화
        presenter = MainPresenter()

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
        Log.d("init", "requestRemoteData()")
        presenter.getRemoteDataByQuery(query)
        repositoryImpl
            .requestRemoteData(query, successCallback = {
                rvMovieAdapter.setAllData(it) // Remote Data를 adapter의 item으로 세팅
                insertLocalData(query, it) //  해당 데이터를 내부 DB에 저장

            }, failCallback = {
                showToast(it.message.toString())
            })

    }

    private fun requestLocalData(query: String) {
        Log.d("init", "requestLocalData()")
        try {
            // 최근 검색한 query 를 PK로 하여 LocalDB에서 데이터 비동기로 얻어오기
            val requestResult =
                repositoryImpl
                    .RequestLocalDataAsync(query)
                    .execute()
                    .get()

            rvMovieAdapter.setAllData(requestResult) // Local Data 세팅

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun insertLocalData(query: String, data: MutableList<MovieData>) {


        repositoryImpl
            .InsertLocalDataAsync(query, data)
            .execute()
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