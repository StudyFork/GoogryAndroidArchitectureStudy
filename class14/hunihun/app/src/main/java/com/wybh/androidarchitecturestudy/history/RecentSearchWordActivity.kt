package com.wybh.androidarchitecturestudy.history

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.wybh.androidarchitecturestudy.*
import com.wybh.androidarchitecturestudy.databinding.ActivityRecentSearchWordBinding

class RecentSearchWordActivity : AppCompatActivity(), RecentSearchWordContract.View {
    private lateinit var binding: ActivityRecentSearchWordBinding
    private val presenter = RecentSearchWordPresenter(this)
    private val historyAdapter = HistoryAdapter {
        presenter.searchCinema(it)
    }
    private val cinemaAdapter: CinemaAdapter =
        CinemaAdapter {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recent_search_word)
        binding.view = this

        initAdapter()
        initData()
    }

    private fun initData() {
        presenter.getSearchWord()
    }

    private fun initAdapter() {
        binding.rvHistory.run {
            adapter = historyAdapter
            addItemDecoration(HorizontalSpaceItemDecoration(20))
            layoutManager = LinearLayoutManager(this@RecentSearchWordActivity).also {
                it.orientation = LinearLayoutManager.HORIZONTAL
            }
        }

        binding.rvSearchList.adapter = cinemaAdapter
    }

    override fun showCinemaList(dataList: List<CinemaItem>) {
        cinemaAdapter.dataClearAndSetting(dataList)
    }

    override fun setSearchHistoryWord(wordList: List<String>) {
        Log.d("jsh", "wordList >>> $wordList")
        historyAdapter.addList(wordList)
    }

    override fun showToastFailMessage(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}