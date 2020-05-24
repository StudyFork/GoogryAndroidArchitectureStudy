package com.project.architecturestudy.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.architecturestudy.R
import com.project.architecturestudy.data.repository.NaverMovieRepositoryImpl
import com.project.architecturestudy.data.source.local.NaverMovieLocalDataSourceImpl
import com.project.architecturestudy.data.source.remote.NaverMovieRemoteDataSourceImpl
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.toast

class SearchActivity : AppCompatActivity(), SearchContract.View {

    private val adapter: SearchAdapter = SearchAdapter()
    private val presenter by lazy {
        val naverMovieLocalDataSource = NaverMovieLocalDataSourceImpl(this)
        val naverMovieRemoteDataSource = NaverMovieRemoteDataSourceImpl()

        SearchPresenter(this, NaverMovieRepositoryImpl(naverMovieLocalDataSource, naverMovieRemoteDataSource))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setRecyclerView()
        setOnClick()

    }

    private fun setOnClick() {
        btn_search.setOnClickListener {
            val searchText = et_search.text.toString()
            presenter.validateSearchWord(searchText)

        }

        adapter.onClick = { item ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
            startActivity(intent)
        }

    }

    private fun setRecyclerView() {
        listview_movie.adapter = adapter
    }

    override fun showSearchWordIsEmpty() {
        toast(getString(R.string.please_write))
    }

    override fun showLocalDataSuccess() {
        toast(getString(R.string.get_local_data_success))
    }

    override fun showLocalDataFailure() {
        toast(getString(R.string.get_local_data_failure))
    }

    override fun showRemoteDataSuccess() {
        toast(getString(R.string.get_data_success))
    }

    override fun showRemoteDataFailure() {
        toast(getString(R.string.get_data_failure))
    }

    override fun onDestroy() {
        presenter.remoteDispose()
        super.onDestroy()
    }
}
