package com.project.architecturestudy.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.project.architecturestudy.R
import com.project.architecturestudy.components.Constants.customTAG
import com.project.architecturestudy.data.model.MovieItem
import com.project.architecturestudy.data.repository.NaverMovieRepositoryImpl
import com.project.architecturestudy.data.source.local.NaverMovieLocalDataSourceImpl
import com.project.architecturestudy.data.source.remote.NaverMovieRemoteDataSourceImpl
import com.project.architecturestudy.databinding.ActivitySearchBinding
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.toast

class SearchActivity : AppCompatActivity(), SearchContract.View {

    private val adapter: SearchAdapter = SearchAdapter()
    private val presenter by lazy {
        val naverMovieLocalDataSource = NaverMovieLocalDataSourceImpl(this)
        val naverMovieRemoteDataSource = NaverMovieRemoteDataSourceImpl()

        SearchPresenter(this, NaverMovieRepositoryImpl(naverMovieLocalDataSource, naverMovieRemoteDataSource))
    }
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        binding.activity = this@SearchActivity
        setRecyclerView()
        onClickAdapterItem()
        presenter.getMovieListFromLocal()

    }

    fun onClickSearchBtn(searchWord: String) {
        Log.d(customTAG, "searchWord:$searchWord")
        presenter.getMovieListFromRemote(this@SearchActivity, searchWord)
    }

    private fun onClickAdapterItem() {
        adapter.onClick = { item ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
            startActivity(intent)
        }

    }

    private fun setRecyclerView() {
        listview_movie.adapter = adapter
    }

    override fun showMovieData(item: List<MovieItem>) {
        adapter.setLocalMovieData(item)
    }

    override fun showSearchKeyWord(result: String, visibility: Int) {
        tv_result_text.visibility = visibility
        tv_result_text.text = result
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
