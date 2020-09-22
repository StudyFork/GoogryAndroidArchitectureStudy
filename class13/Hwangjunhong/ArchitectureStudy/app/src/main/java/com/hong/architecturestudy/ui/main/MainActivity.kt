package com.hong.architecturestudy.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.hong.architecturestudy.R
import com.hong.architecturestudy.data.model.MovieData
import com.hong.architecturestudy.data.repository.RepositoryDataSourceImpl
import com.hong.architecturestudy.data.source.local.LocalDataSourceImpl
import com.hong.architecturestudy.data.source.remote.RemoteDataSourceImpl
import com.hong.architecturestudy.databinding.ActivityMainBinding
import com.hong.architecturestudy.ext.hideKeyboard
import com.hong.architecturestudy.ui.base.BaseActivity
import com.hong.architecturestudy.ui.main.adapter.MovieAdapter
import com.hong.architecturestudy.ui.moviedialog.MovieListDialogFragment
import com.hong.architecturestudy.utils.log

typealias GetMovieTitle = (String) -> Unit

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main), MainContract.View {

    private val mainPresenter: MainContract.Presenter by lazy {
        val remoteDataSourceImpl = RemoteDataSourceImpl()
        val localDataSourceImpl = LocalDataSourceImpl()
        MainPresenter(this, RepositoryDataSourceImpl(localDataSourceImpl, remoteDataSourceImpl))
    }

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter()
    }

    private val getMovieData: GetMovieTitle = { movieTitle ->
        mainPresenter.getMovieList(movieTitle)
        hideKeyboard(this, binding.editSearch)
    }

    private val fragmentFactory = FragmentFactoryImpl { getMovieData(it) }
    private val movieListDialogFragment = MovieListDialogFragment.getInstance {
        getMovieData(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)

        binding.activity = this
        setRecyclerView()

    }

    fun searchMovie(view: View) {
        val keyword = binding.editSearch.text.toString()
        mainPresenter.getMovieList(keyword)
        binding.editSearch.text.clear()
        hideKeyboard(this, binding.editSearch)
    }

    fun getRecentSearchList(view: View) {
        movieListDialogFragment.show(supportFragmentManager, "dialog")
    }

    private fun setRecyclerView() {
        binding.rvMoviesList.adapter = movieAdapter
        binding.rvMoviesList.setHasFixedSize(true)
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

    override fun showMovieList(movieData: List<MovieData>) {
        movieAdapter.setData(movieData)
    }

}