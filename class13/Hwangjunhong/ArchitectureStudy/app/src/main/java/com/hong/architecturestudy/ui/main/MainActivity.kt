package com.hong.architecturestudy.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.hong.architecturestudy.R
import com.hong.architecturestudy.data.repository.RepositoryDataSourceImpl
import com.hong.architecturestudy.data.source.local.LocalDataSourceImpl
import com.hong.architecturestudy.data.source.remote.RemoteDataSourceImpl
import com.hong.architecturestudy.ext.hideKeyboard
import com.hong.architecturestudy.ui.main.adapter.MovieAdapter
import com.hong.architecturestudy.ui.moviedialog.MovieListDialogFragment
import com.hong.architecturestudy.utils.log
import kotlinx.android.synthetic.main.activity_main.*

typealias GetMovieTitle = (String) -> Unit

class MainActivity : AppCompatActivity(), MainContract.View {

    private val mainPresenter: MainPresenter by lazy {
        val remoteDataSourceImpl = RemoteDataSourceImpl()
        val localDataSourceImpl = LocalDataSourceImpl()
        MainPresenter(this, RepositoryDataSourceImpl(localDataSourceImpl, remoteDataSourceImpl), movieAdapter)
    }

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter()
    }

    private val getMovieData: GetMovieTitle = { movieTitle ->
        mainPresenter.getMovieList(movieTitle)
        hideKeyboard(this, edit_search)
    }

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
            mainPresenter.getMovieList(keyword)
            edit_search.text.clear()
            hideKeyboard(this, edit_search)
        }

        btn_search_list.setOnClickListener {
            movieListDialogFragment.show(supportFragmentManager, "dialog")
        }
    }

    private fun setRecyclerView() {
        rv_movies_list.adapter = movieAdapter
        rv_movies_list.setHasFixedSize(true)
        movieAdapter.onClick = { movieData ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movieData.link))
            startActivity(intent)
        }
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

    override fun showQueryEmpty() {
        Toast.makeText(this, "영화 제목을 입력해 주세요", Toast.LENGTH_LONG).show()
    }


    override fun showError(throwable: Throwable) {
        log(throwable.toString())
    }
}