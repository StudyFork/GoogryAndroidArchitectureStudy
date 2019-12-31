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
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.fragment_page.*


class MovieFragment : BaseFragment(R.layout.fragment_page), PageContract.View<MovieItem> {
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var presenter: MoviePresenter
    private lateinit var movieList: List<MovieItem>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start()
        setUpBuuttonClickListener()
    }

    private fun start() {
        presenter = MoviePresenter(this)
        button_home_search.text = "블로그 검색"
        movieAdapter = MovieAdapter(this::onMovieItemClick)
        with(recyclerview_home) {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = movieAdapter
            checkHistoryItems()
        }
    }

    private fun checkHistoryItems() {
        if (Hawk.get("movieList", null) != null) {
            movieList = Hawk.get("movieList")
            movieAdapter.resetItems(movieList)
            setEmptyView(false)
        } else {
            setEmptyView(true)
        }
    }

    private fun setUpBuuttonClickListener() {
        button_home_search.setOnClickListener {
            if (edittext_home_searchedit.text.isBlank()) {
                toast("검색어를 입력하세요")
            } else {
                toast("검색어 :${edittext_home_searchedit.text}")
                presenter.loadData(edittext_home_searchedit.text.toString())
            }
        }
    }

    override fun getItems(items: List<MovieItem>) {
        if (items.size == 0) {
            setEmptyView(true)
            Hawk.put("movieList", null)
            movieAdapter.removeAll()
        } else {
            setEmptyView(false)
            movieList = items
            movieAdapter.resetItems(items)
            Hawk.put("movieList", movieList)
        }
    }

    override fun getError(message: String) = toast(message)

}
