package com.example.kyudong3.ui

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import com.example.kyudong3.R
import com.example.kyudong3.adapter.SearchMovieRvAdapter
import com.example.kyudong3.data.local.MovieDatabase
import com.example.kyudong3.data.repository.MovieRepositoryImpl
import com.example.kyudong3.databinding.ActivityMainBinding
import com.example.kyudong3.extension.toast
import com.example.kyudong3.mapper.MovieLocalMapper
import com.example.kyudong3.mapper.MovieRemoteMapper
import com.example.kyudong3.util.RecyclerViewItemDivider
import com.example.kyudong3.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val movieRvAdapter: SearchMovieRvAdapter by lazy {
        SearchMovieRvAdapter()
    }

    private val mainViewModel: MainViewModel by lazy {
        MainViewModel(
            MovieRepositoryImpl(
                MovieDatabase.getInstance(applicationContext).movieDao(),
                MovieRemoteMapper(),
                MovieLocalMapper()
            )
        )
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = mainViewModel

        setMovieRecyclerView()
        initVmCallback()

        binding.searchQuery.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_UNSPECIFIED, EditorInfo.IME_ACTION_GO -> {
                    mainViewModel.searchMovie()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun setMovieRecyclerView() {
        binding.searchRv.apply {
            adapter = movieRvAdapter
            addItemDecoration(RecyclerViewItemDivider(this@MainActivity))
        }
    }

    private fun initVmCallback() {
        with(mainViewModel) {
            movies.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    movieRvAdapter.setMovieList(movies.get())
                    movieRvAdapter.notifyDataSetChanged()
                }
            })

            invalidSearchQuery.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    toast("검색어를 1자 이상 입력해주세요!")
                }
            })

            emptySearchResult.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    toast("검색결과가 없습니다")
                }
            })

            showNetworkError.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    toast("네트워크 오류가 발생했습니다")
                }
            })
        }
    }
}
