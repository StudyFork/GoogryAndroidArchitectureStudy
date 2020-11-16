package com.wybh.androidarchitecturestudy.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.wybh.androidarchitecturestudy.CinemaAdapter
import com.wybh.androidarchitecturestudy.CinemaItem
import com.wybh.androidarchitecturestudy.R

class MainActivity : AppCompatActivity(), MainContract.View {
    override fun showToastFailMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showCinemaList(dataList: List<CinemaItem>) {
        // adapter list clear
        cinemaAdapter.dataClear()
        cinemaAdapter.addList(dataList)
        // recyclerview 갱신
        cinemaAdapter.notifyDataSetChanged()

        // 키보드 내리기
        imm.hideSoftInputFromWindow(etSearchWord.windowToken, 0)
    }
    
    private lateinit var etSearchWord: EditText
    private lateinit var btnSearch: Button
    private lateinit var rvCinema: RecyclerView
    private lateinit var imm: InputMethodManager

    private val presenter = MainPresenter(this)
    private val cinemaAdapter: CinemaAdapter =
        CinemaAdapter {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initAdapter()
        initViewClickListener()
    }

    override fun onDestroy() {
        presenter.removeCompositeDisposable()
        super.onDestroy()
    }

    private fun initView() {
        etSearchWord = findViewById(R.id.et_searchWord)
        btnSearch = findViewById(R.id.btn_search)
        rvCinema = findViewById(R.id.rv_cinema)

        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }
    
    private fun initAdapter() {
        rvCinema.run {
            adapter = cinemaAdapter
        }
    }

    private fun initViewClickListener() {
        btnSearch.setOnClickListener {
            presenter.searchCinema(etSearchWord.text.toString())
        }
    }
}
