package com.project.architecturestudy.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.architecturestudy.R
import com.project.architecturestudy.base.BaseActivity
import com.project.architecturestudy.data.model.MovieItem
import com.project.architecturestudy.data.repository.NaverMovieRepositoryImpl
import com.project.architecturestudy.data.source.local.NaverMovieLocalDataSourceImpl
import com.project.architecturestudy.data.source.local.room.MovieDataBase
import com.project.architecturestudy.data.source.remote.NaverMovieRemoteDataSourceImpl
import com.project.architecturestudy.databinding.ActivitySearchBinding
import com.project.architecturestudy.databinding.MovieItemBinding
import org.jetbrains.anko.toast

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchViewModel>(R.layout.activity_search) {

    override val vm: SearchViewModel by lazy {
        val naverMovieLocalDataSource = NaverMovieLocalDataSourceImpl.getInstance(MovieDataBase.getInstance(applicationContext).getMovieDao())
        val naverMovieRemoteDataSource = NaverMovieRemoteDataSourceImpl
        val repository = NaverMovieRepositoryImpl.getInstance(naverMovieLocalDataSource, naverMovieRemoteDataSource)
        val viewModelProvider = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SearchViewModel(repository) as T
            }
        })
        viewModelProvider[SearchViewModel::class.java]
    }
    private lateinit var adapter: SearchAdapter<MovieItemBinding, MovieItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = vm
        binding.lifecycleOwner = this
        setRecyclerView()
        onClickAdapterItem()

        vm.searchWord.observe(this, Observer {
            vm.invokeTextChanged()
        })

        vm.showToast.observe(this, Observer {
            Log.d("observer", "$it")
            toast(getString(it))
        })
    }

    private fun onClickAdapterItem() {
        adapter.onClick = { item ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
            startActivity(intent)
        }
    }

    private fun setRecyclerView() {
        adapter = SearchAdapter(R.layout.movie_item)
        binding.listviewMovie.adapter = adapter
    }
}