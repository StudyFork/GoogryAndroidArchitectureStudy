package com.mtjin.androidarchitecturestudy.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mtjin.androidarchitecturestudy.R
import com.mtjin.androidarchitecturestudy.base.BaseActivity
import com.mtjin.androidarchitecturestudy.databinding.ActivityMovieSearchBinding
import com.mtjin.androidarchitecturestudy.utils.MyApplication


class MovieSearchActivity : BaseActivity() {

    private lateinit var binding: ActivityMovieSearchBinding
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var myApplication: MyApplication
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener
    private lateinit var viewModel: MovieSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_search)
        inject()
        initViewModelCallback()
        initAdapter()
    }

    private fun inject() {
        myApplication = application as MyApplication
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_search)
        viewModel = MovieSearchViewModel(myApplication.movieRepository)
        binding.vm = viewModel
    }

    private fun initAdapter() {
        movieAdapter = MovieAdapter { movie ->
            Intent(Intent.ACTION_VIEW, Uri.parse(movie.link)).takeIf {
                it.resolveActivity(packageManager) != null
            }?.run(this::startActivity)
        }
        scrollListener = object :
            EndlessRecyclerViewScrollListener(binding.rvMovies.layoutManager as LinearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                viewModel.requestPagingMovie(totalItemsCount + 1)
            }
        }
        binding.rvMovies.addOnScrollListener(scrollListener)
        binding.rvMovies.adapter = movieAdapter
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            toastMsg.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    when (toastMsg.get()) {
                        MovieSearchViewModel.MessageSet.LAST_PAGE -> showToast(getString(R.string.last_page_msg))
                        MovieSearchViewModel.MessageSet.EMPTY_QUERY -> showToast(getString(R.string.search_input_query_msg))
                        MovieSearchViewModel.MessageSet.NETWORK_ERROR -> showToast(getString(R.string.network_error_msg))
                        MovieSearchViewModel.MessageSet.SUCCESS -> showToast(getString(R.string.load_movie_success_msg))
                        MovieSearchViewModel.MessageSet.NO_RESULT -> showToast(getString(R.string.no_movie_error_msg))
                    }
                    toastMsg.set(MovieSearchViewModel.MessageSet.BASIC)
                }
            })
//            lastPageMsg.addOnPropertyChangedCallback(object :
//                Observable.OnPropertyChangedCallback() {
//                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
//                    showToast(getString(R.string.last_page_msg))
//                }
//            })
//            emptyQueryMsg.addOnPropertyChangedCallback(object :
//                Observable.OnPropertyChangedCallback() {
//                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
//                    showToast(getString(R.string.search_input_query_msg))
//                }
//            })
//            noResultMsg.addOnPropertyChangedCallback(object :
//                Observable.OnPropertyChangedCallback() {
//                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
//                    showToast(getString(R.string.no_movie_error_msg))
//                }
//            })
//            networkErrorMsg.addOnPropertyChangedCallback(object :
//                Observable.OnPropertyChangedCallback() {
//                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
//                    showToast(getString(R.string.network_error_msg))
//                }
//            })
//            successMsg.addOnPropertyChangedCallback(object :
//                Observable.OnPropertyChangedCallback() {
//                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
//                    showToast(getString(R.string.load_movie_success_msg))
//                }
//            })
            scrollRestateState.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    if (scrollRestateState.get()) {
                        scrollResetState()
                        scrollRestateState.set(false)
                    }
                }
            })
        }
    }

    fun scrollResetState() {
        scrollListener.resetState()
    }
}
