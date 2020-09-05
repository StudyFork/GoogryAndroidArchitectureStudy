package com.hong.architecturestudy.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
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

    private val getMovieData: GetMovieTitle = { movieTitle ->
        repositoryDataSourceImpl.getMovieList(movieTitle, { movieData ->
            adapter.setData(movieData)
            hideKeyboard(this, edit_search)
        }, {
            Toast.makeText(this, "검색 실패", Toast.LENGTH_LONG).show()
        })
    }

    private val adapter = MovieAdapter()
    private val fragmentFactory = FragmentFactoryImpl { getMovieData(it) }
    private val movieListDialogFragment = MovieListDialogFragment.getInstance {
        getMovieData(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = fragmentFactory
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

    class FragmentFactoryImpl(private val getMovieTitle: (String) -> Unit) : FragmentFactory() {
        override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
            return when (className) {
                MovieListDialogFragment::class.java.name -> MovieListDialogFragment.getInstance(
                    getMovieTitle
                )
                else -> super.instantiate(classLoader, className)
            }
        }
    }
}