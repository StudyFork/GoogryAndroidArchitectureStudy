package com.hong.architecturestudy.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hong.architecturestudy.R
import com.hong.architecturestudy.data.repository.RepositoryDataSource
import com.hong.architecturestudy.data.repository.RepositoryDataSourceImpl
import com.hong.architecturestudy.data.source.local.LocalDataSourceImpl
import com.hong.architecturestudy.data.source.local.MovieDatabase
import com.hong.architecturestudy.data.source.remote.RemoteDataSourceImpl
import com.hong.architecturestudy.ext.hideKeyboard
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

typealias GetMovieTitle = (String) -> Unit

class MainActivity : AppCompatActivity() {

    private val repositoryDataSourceImpl: RepositoryDataSource by lazy {
        val remoteDataSourceImpl = RemoteDataSourceImpl()
        val localDataSourceImpl = LocalDataSourceImpl()
        RepositoryDataSourceImpl(localDataSourceImpl, remoteDataSourceImpl)
    }

    private val adapter = MovieAdapter()

    private val movieListDialogFragment = MovieListDialogFragment.newInstance { movieTitle ->
        repositoryDataSourceImpl.getMovieList(movieTitle, { movieData ->
            adapter.setData(movieData)
            hideKeyboard(this, edit_search)
        }, {
            Toast.makeText(this, "검색 실패", Toast.LENGTH_LONG).show()
        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerView()

        btn_search.setOnClickListener {
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
            movieListDialogFragment.show(supportFragmentManager, "dialog")

        }
    }

    private fun setRecyclerView() {
        rv_movies_list.adapter = adapter
        rv_movies_list.setHasFixedSize(true)
        adapter.onClick = { movieData ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movieData.link))
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        CoroutineScope(Dispatchers.IO).cancel()
        MovieDatabase.destroyInstance()
    }
}