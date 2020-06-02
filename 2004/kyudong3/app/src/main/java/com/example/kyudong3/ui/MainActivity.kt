package com.example.kyudong3.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.kyudong3.R
import com.example.kyudong3.adapter.SearchMovieRvAdapter
import com.example.kyudong3.databinding.ActivityMainBinding
import com.example.kyudong3.extension.toast
import com.example.kyudong3.util.RecyclerViewItemDivider
import com.example.kyudong3.viewModel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val movieRvAdapter: SearchMovieRvAdapter by lazy {
        SearchMovieRvAdapter()
    }

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        mainViewModel.errorSearchResult.observe(this, Observer { message ->
            toast(message)
        })
    }
}
