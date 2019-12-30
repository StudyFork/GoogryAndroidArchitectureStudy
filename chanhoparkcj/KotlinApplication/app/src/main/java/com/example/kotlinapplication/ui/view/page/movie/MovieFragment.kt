package com.example.kotlinapplication.ui.view.page.movie

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinapplication.R
import com.example.kotlinapplication.data.model.MovieItem
import com.example.kotlinapplication.ui.base.BaseFragment
import com.example.kotlinapplication.ui.view.home.presenter.MoviePresenter
import com.example.kotlinapplication.ui.view.page.PageContract
import kotlinx.android.synthetic.main.fragment_page.*


class MovieFragment : BaseFragment(R.layout.fragment_page), MovieListAdapter.ItemListener, PageContract.View<MovieItem> {
    private lateinit var movieAdapter: MovieListAdapter
    private lateinit var presenter: MoviePresenter
    private lateinit var movieList: List<MovieItem>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start()
        setUpBuuttonClickListener()
    }

    private fun start() {
        presenter = MoviePresenter(this)
        home_search_btn.text = "블로그 검색"
        movieAdapter = MovieListAdapter(this)
        with(home_recyclerview) {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = movieAdapter
        }
    }

    private fun setUpBuuttonClickListener() {
        home_search_btn.setOnClickListener {
            if (home_search_edit.text.isBlank()) {
                toast("검색어를 입력하세요")
            } else {
                toast("검색어 :${home_search_edit.text}")
                presenter.loadData(home_search_edit.text.toString())
            }
        }
    }
    override fun getItems(items: List<MovieItem>) {
        movieList = items
        movieAdapter.addAllItems(items)
    }

    override fun onMovieItemClick(movieItems: MovieItem) {
        toast(movieItems.link)
        webLink(movieItems.link)
    }


    override fun getError(message: String) {
        toast(message)
    }
}
