package com.example.androidarchitecturestudy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.androidarchitecturestudy.adapter.RecentSearchedRecyclerviewAdapter
import com.example.androidarchitecturestudy.data.repository.MovieRepositoryImpl
import com.example.androidarchitecturestudy.databinding.ActivityRecentSearchBinding
import com.example.androidarchitecturestudy.presenter.SearchedMovieContract
import com.example.androidarchitecturestudy.presenter.SearchedMoviePresenter
import com.example.androidarchitecturestudy.room.SearchedDataBase
import kotlinx.android.synthetic.main.activity_recent_search.*

class RecentSearchActivity :AppCompatActivity(),SearchedMovieContract.View {

    private val searchedMoviePresenter = SearchedMoviePresenter(this, MovieRepositoryImpl())

    // 리사이클러뷰 어뎁터
    private lateinit var recyclerViewAdapter: RecentSearchedRecyclerviewAdapter
    private lateinit var binding: ActivityRecentSearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBindingSet()
        setRecyclerView()
        getSearchedMovieList()

    }
    private fun dataBindingSet(){
        binding = DataBindingUtil.setContentView(this,R.layout.activity_recent_search)
        binding.thisActivity = this
    }


    private fun getSearchedMovieList() {
        val database = SearchedDataBase.getInstance(applicationContext)
        searchedMoviePresenter.getSearchedMovieList(database!!)
    }

    override fun updateRecyclerView(recentSearchMovieList: List<String>) {
        recyclerViewAdapter.setRecentSearchedMovieData(recentSearchMovieList)
    }

    private fun sendSelectedQuery(searchedQuery: String) {
        val intent = Intent().apply {
            this.putExtra(SELECTED_MOVIE_QUERY_DATA, searchedQuery)
        }
        setResult(RESULT_OK, intent)
        finish()
    }

    // 리사이클러뷰 세팅
    private fun setRecyclerView() {
        recyclerViewAdapter = RecentSearchedRecyclerviewAdapter()
        rcy_recent_searched_movie_title.apply {
            adapter = recyclerViewAdapter
        }
        recyclerViewAdapter.setOnItemClickListener(object :
            RecentSearchedRecyclerviewAdapter.OnItemClickListener {
            override fun onItemClick(searchedQuery: String) {
                sendSelectedQuery(searchedQuery)
            }
        })

    }

}