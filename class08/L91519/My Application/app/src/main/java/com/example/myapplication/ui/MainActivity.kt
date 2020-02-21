package com.example.myapplication.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.data.MovieDatabase
import com.example.myapplication.data.repository.NaverRepositoryImpl
import com.example.myapplication.data.source.NaverLocalDataSourceImpl
import com.example.myapplication.data.source.NaverRemoteDataSourceImpl
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieRecyclerViewAdpater
    private val vm: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(
                    NaverRepositoryImpl.getInstance(
                        NaverRemoteDataSourceImpl.getInstance(applicationContext),
                        NaverLocalDataSourceImpl.getInstance(
                            MovieDatabase.getInstance(
                                applicationContext
                            ).movieDao()
                        )
                    )
                ) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        adapter = MovieRecyclerViewAdpater()

        binding.vm = vm
        binding.rvMovieList.adapter = adapter
        observableProperty()

        vm.getRecentData()

    }

    private fun observableProperty() {

        vm.errorQueryBlank.observe(this@MainActivity, Observer {
            showToast(getString(R.string.query_none))
        })

        vm.errorFailSearch.observe(this@MainActivity, Observer {
            if (vm.resultEmpty.value as Boolean) {
                showToast(getString(R.string.movie_search_fail))
            }
        })
        vm.resultEmpty.observe(this@MainActivity, Observer {
            if (vm.resultEmpty.value as Boolean) {
                showToast(getString(R.string.result_none))
            }
        })
        vm.searchResultList.observe(this@MainActivity, Observer {
            vm.searchResultList.value?.let { adapter.setItems(it) }
        })
    }

    private fun showToast(toast: String) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show()
    }
}
