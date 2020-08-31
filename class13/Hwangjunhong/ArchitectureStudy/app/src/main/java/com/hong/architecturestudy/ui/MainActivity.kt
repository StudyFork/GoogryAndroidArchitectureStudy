package com.hong.architecturestudy.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.hong.architecturestudy.R
import com.hong.architecturestudy.data.repository.RepositoryDataSourceImpl
import com.hong.architecturestudy.data.source.local.LocalDataSourceImpl
import com.hong.architecturestudy.data.source.local.MovieDatabase
import com.hong.architecturestudy.data.source.remote.RemoteDataSourceImpl
import com.hong.architecturestudy.ext.hideKeyboard
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val repositoryDataSourceImpl: RepositoryDataSourceImpl by lazy {
        val remoteDataSourceImpl = RemoteDataSourceImpl()
        val localDataSourceImpl = LocalDataSourceImpl()
        RepositoryDataSourceImpl(localDataSourceImpl, remoteDataSourceImpl)
    }

    private val adapter = MovieAdapter()
    private val movieSearchListAdapter = MovieSearchListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerView()

        btn_search.setOnClickListener {
            rv_movies_list.isVisible = true
            rv_search_list.isVisible = false
            val keyword = edit_search.text.toString()
            if (keyword.isBlank()) {
                Toast.makeText(this, "영화 제목을 입력해 주세요", Toast.LENGTH_LONG).show()
            } else {
                repositoryDataSourceImpl.getMovieList(keyword,
                    {
                        edit_search.text.clear()
                        adapter.setData(it)
                        hideKeyboard(this, edit_search)
                        repositoryDataSourceImpl.saveData(keyword, this)

                    }, {
                        Toast.makeText(this, "검색 실패", Toast.LENGTH_LONG).show()
                    })
            }
        }

        btn_search_list.setOnClickListener {
            rv_movies_list.isVisible = false
            rv_search_list.isVisible = true

            repositoryDataSourceImpl.loadData(this, Observer {
                movieSearchListAdapter.setList(it)
            }, this)

        }
    }

    private fun setRecyclerView() {
        rv_movies_list.adapter = adapter
        rv_movies_list.setHasFixedSize(true)
        adapter.onClick = { movieData ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movieData.link))
            startActivity(intent)
        }

        rv_search_list.adapter = movieSearchListAdapter
        rv_search_list.setHasFixedSize(true)

        movieSearchListAdapter.onClick = { title ->
            rv_search_list.isVisible = false
            edit_search.text.clear()
            repositoryDataSourceImpl.getMovieList(title,
                {
                    rv_movies_list.isVisible = true
                    adapter.setData(it)
                    hideKeyboard(this, edit_search)
                }, {
                    Toast.makeText(this, "검색 실패", Toast.LENGTH_LONG).show()
                })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        CoroutineScope(Dispatchers.IO).cancel()
        MovieDatabase.destroyInstance()
    }
}