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

class SearchActivity : AppCompatActivity() {

    private val adapter: SearchAdapter = SearchAdapter()

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
            if (et_search.text.toString().isEmpty()) {
                toast(getString(R.string.please_write))
                return@setOnClickListener
            }

            naverMovieRepositoryImpl.getMovieList(et_search.text.toString(),
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
}
