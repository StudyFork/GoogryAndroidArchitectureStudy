package com.project.architecturestudy.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.project.architecturestudy.R
import com.project.architecturestudy.data.repository.NaverMovieRepositoryImpl
import com.project.architecturestudy.data.source.local.NaverMovieLocalDataSourceImpl
import com.project.architecturestudy.data.source.remote.NaverMovieRemoteDataSourceImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.toast

class SearchActivity : AppCompatActivity(), SearchContract.View {

    private val adapter: SearchAdapter = SearchAdapter()
    private val presenter by lazy {
        val naverMovieLocalDataSource = NaverMovieLocalDataSourceImpl(this)
        val naverMovieRemoteDataSource = NaverMovieRemoteDataSourceImpl()

        SearchPresenter(this, NaverMovieRepositoryImpl(naverMovieLocalDataSource, naverMovieRemoteDataSource))
    }

    private val naverMovieRepositoryImpl by lazy {

        val naverMovieLocalDataSource = NaverMovieLocalDataSourceImpl(this)
        val naverMovieRemoteDataSource = NaverMovieRemoteDataSourceImpl()
        NaverMovieRepositoryImpl(naverMovieLocalDataSource, naverMovieRemoteDataSource)
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

            if (searchText.isEmpty()) {
                toast(getString(R.string.please_write))
                return@setOnClickListener
            }

            naverMovieRepositoryImpl.getMovieList(searchText,
                onGetRemoteData = { single ->
                    single.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                            {

                                adapter.setRemoteMovieData(it.items)
                                toast(getString(R.string.get_data_success))
                            }, { t ->

                                toast(getString(R.string.get_data_failure))
                                Log.d("bsjbsj", t.toString())
                            })

                })
        }

        adapter.onClick = { item ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
            startActivity(intent)
        }

    }

    private fun setRecyclerView() {
        listview_movie.adapter = adapter
        naverMovieRepositoryImpl.getCashedMovieList(
            onSuccess = {
                adapter.setLocalMovieData(it)
            },
            onFailure = {
                Log.d("bsjbsj", "Throwable:$it")
            })

    }

    override fun onDestroy() {
        naverMovieRepositoryImpl.dispose()
        super.onDestroy()
    }

    override fun showSearchWordIsEmpty(emptyMsg: String) {
        toast(emptyMsg)
    }

    override fun showLocalDataSuccess(successMsg: String) {
        toast(successMsg)
    }

    override fun showLocalDataFailure(failureMsg: String) {
        toast(failureMsg)
    }

    override fun showRemoteDataSuccess(successMsg: String) {
        toast(successMsg)
    }

    override fun showRemoteDataFailure(failureMsg: String) {
        toast(failureMsg)
    }
}
