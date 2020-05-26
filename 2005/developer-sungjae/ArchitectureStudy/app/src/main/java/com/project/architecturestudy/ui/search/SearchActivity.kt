package com.project.architecturestudy.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.architecturestudy.R
import com.project.architecturestudy.data.model.MovieItem
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
        presenter.getMovieListFromLocal()

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

    override fun showLocalMovieData(item: ArrayList<MovieItem>) {
        adapter.setLocalMovieData(item)
    }

    override fun showRemoteMovieData(item: List<MovieItem>) {
        adapter.setRemoteMovieData(item)
    }


    override fun showSearchWordIsEmptyMsg() {
        toast(getString(R.string.please_write))
    }

    override fun showLocalDataSuccessMsg() {
        toast(getString(R.string.get_local_data_success))
    }

    override fun showLocalDataFailureMsg() {
        toast(getString(R.string.get_local_data_failure))
    }

    override fun showRemoteDataSuccessMsg() {
        toast(getString(R.string.get_data_success))
    }

    override fun showRemoteDataFailureMsg() {
        toast(getString(R.string.get_data_failure))
    }

    override fun onDestroy() {
        presenter.remoteDispose()
        super.onDestroy()
    }
}
