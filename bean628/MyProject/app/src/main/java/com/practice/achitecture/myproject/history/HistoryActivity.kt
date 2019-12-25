package com.practice.achitecture.myproject.history

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import com.practice.achitecture.myproject.R
import com.practice.achitecture.myproject.base.BaseActivity
import com.practice.achitecture.myproject.data.source.NaverRepository
import com.practice.achitecture.myproject.data.source.local.NaverDatabase
import com.practice.achitecture.myproject.data.source.local.NaverLocalDataSourceImpl
import com.practice.achitecture.myproject.data.source.remote.NaverRemoteDataSourceImpl
import com.practice.achitecture.myproject.databinding.ActivityHistoryBinding
import com.practice.achitecture.myproject.enum.SearchType
import com.practice.achitecture.myproject.main.SearchBlogAndNewsAdapter
import com.practice.achitecture.myproject.main.SearchMovieAndBookAdapter
import com.practice.achitecture.myproject.main.SearchedItemClickListener
import com.practice.achitecture.myproject.makeToast
import com.practice.achitecture.myproject.model.SearchedItem
import com.practice.achitecture.myproject.network.RetrofitClient
import com.practice.achitecture.myproject.util.AppExecutors
import common.NAVER_API_BASE_URL

class HistoryActivity :
    BaseActivity<HistoryContract.Presenter, ActivityHistoryBinding>(R.layout.activity_history),
    View.OnClickListener,
    HistoryContract.View {

    override val presenter: HistoryContract.Presenter by lazy {
        HistoryPresenter(
            this,
            NaverRepository.getInstance(
                NaverRemoteDataSourceImpl(RetrofitClient(NAVER_API_BASE_URL).makeRetrofitServiceForNaver()),
                NaverLocalDataSourceImpl.getInstance(
                    AppExecutors(),
                    NaverDatabase.getInstance(applicationContext).naverDao(),
                    this.cacheDir.absolutePath
                )
            )
        )
    }

    private var searchMovieAndBookAdapter: SearchMovieAndBookAdapter? = null
    private var searchBlogAndNewsAdapter: SearchBlogAndNewsAdapter? = null
    private val searchedItemListener: SearchedItemClickListener =
        object : SearchedItemClickListener {
            override fun onItemClick(item: SearchedItem) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.link)))
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerOnClickListener()
        initAdapter()
        presenter.loadHistory(SearchType.MOVIE)
    }

    private fun initAdapter() {
        searchMovieAndBookAdapter = SearchMovieAndBookAdapter(searchedItemListener)
        searchBlogAndNewsAdapter = SearchBlogAndNewsAdapter(searchedItemListener)
    }

    private fun registerOnClickListener() {
        binding.btnSearchTypeMovie.setOnClickListener(this)
        binding.btnSearchTypeBook.setOnClickListener(this)
        binding.btnSearchTypeBlog.setOnClickListener(this)
        binding.btnSearchTypeNews.setOnClickListener(this)
    }

    override fun showSearchResultBlogOrNews(items: List<SearchedItem>) {
        searchBlogAndNewsAdapter?.notifyDataSetChanged(items)
        binding.rvSearchedList.adapter = searchBlogAndNewsAdapter
    }

    override fun showSearchResultMovieOrBook(items: List<SearchedItem>) {
        searchMovieAndBookAdapter?.notifyDataSetChanged(items)
        binding.rvSearchedList.adapter = searchMovieAndBookAdapter
    }

    override fun historyEmpty(@StringRes stringId: Int) {
        makeToast(stringId)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_search_type_movie ->
                presenter.loadHistory(SearchType.MOVIE)
            R.id.btn_search_type_book ->
                presenter.loadHistory(SearchType.BOOK)
            R.id.btn_search_type_blog ->
                presenter.loadHistory(SearchType.BLOG)
            R.id.btn_search_type_news ->
                presenter.loadHistory(SearchType.NEWS)
        }
    }

}