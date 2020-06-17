package com.example.architecture.activity.search

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.architecture.R
import com.example.architecture.activity.search.adapter.MovieAdapter
import com.example.architecture.data.repository.NaverRepositoryImpl
import com.example.architecture.databinding.ActivitySearchBinding
import com.example.architecture.ext.debounce
import com.example.architecture.provider.ResourceProviderImpl
import com.example.architecture.util.ConstValue.Companion.AUTO_SEARCH_TIME
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    @Suppress("UNCHECKED_CAST")
    private val vm: SearchViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SearchViewModel(
                    NaverRepositoryImpl(this@SearchActivity.applicationContext)
                    , ResourceProviderImpl(this@SearchActivity.applicationContext)
                ) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySearchBinding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        binding.vm = vm
        binding.lifecycleOwner = this

        setupRecyclerview()
        setupViewModelObserve()
    }

    private fun setupRecyclerview() {
        rv_search_movieList.adapter = MovieAdapter()
    }

    private fun setupViewModelObserve() {
        showToast()
        showSearchMovie()
    }

    private fun showToast() {
        vm.toastMessage
            .observe(this, Observer { message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            })
    }

    private fun showSearchMovie() {
        vm.keyword
            .debounce(AUTO_SEARCH_TIME)
            .observe(this, Observer {
                vm::searchMovie.invoke()
            })
    }

    override fun onResume() {
        super.onResume()
        bindViewModel()
    }

    private fun bindViewModel() {
        vm.bindViewModel()
    }

    override fun onPause() {
        unbindViewModel()
        super.onPause()
    }

    private fun unbindViewModel() {
        vm.unbindViewModel()
    }
}
