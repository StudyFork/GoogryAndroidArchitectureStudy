package com.example.kyudong3.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kyudong3.R
import com.example.kyudong3.adapter.SearchMovieRvAdapter
import com.example.kyudong3.data.local.MovieDatabase
import com.example.kyudong3.data.repository.MovieRepositoryImpl
import com.example.kyudong3.databinding.ActivityMainBinding
import com.example.kyudong3.extension.toast
import com.example.kyudong3.mapper.MovieLocalMapper
import com.example.kyudong3.mapper.MovieRemoteMapper
import com.example.kyudong3.provider.ResourceProviderImpl
import com.example.kyudong3.util.RecyclerViewItemDivider
import com.example.kyudong3.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val movieRvAdapter: SearchMovieRvAdapter by lazy {
        SearchMovieRvAdapter()
    }

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(
                    MovieRepositoryImpl(
                        MovieDatabase.getInstance(applicationContext).movieDao(),
                        MovieRemoteMapper(),
                        MovieLocalMapper()
                    ),
                    ResourceProviderImpl(this@MainActivity.applicationContext)
                ) as T
            }
        })[MainViewModel::class.java]

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.run {
            lifecycleOwner = this@MainActivity
            vm = mainViewModel
        }

        setMovieRecyclerView()
        observeViewModel()
    }

    private fun setMovieRecyclerView() {
        binding.searchRv.apply {
            adapter = movieRvAdapter
            addItemDecoration(RecyclerViewItemDivider(this@MainActivity))
        }
    }

    private fun observeViewModel() {
        mainViewModel.invalidSearchQuery.observe(this, Observer { invalid ->
            showToast(invalid)
        })
        mainViewModel.emptySearchResult.observe(this, Observer { empty ->
            showToast(empty)
        })
        mainViewModel.showNetworkError.observe(this, Observer { networkError ->
            showToast(networkError)
        })
    }

    private fun showToast(message: String) {
        toast(message)
    }
}
