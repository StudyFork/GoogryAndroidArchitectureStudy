package com.example.myapplication.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import com.example.myapplication.MovieDatabase
import com.example.myapplication.R
import com.example.myapplication.data.repository.NaverRepositoryImpl
import com.example.myapplication.data.source.NaverLocalDataSourceImpl
import com.example.myapplication.data.source.NaverRemoteDataSourceImpl
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieRecyclerViewAdpater
    private val vm: MainViewModel by lazy {
        MainViewModel(
            NaverRepositoryImpl(
                NaverRemoteDataSourceImpl,
                NaverLocalDataSourceImpl(
                    MovieDatabase.getInstance(applicationContext).movieDao()
                )
            )
        )
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

        vm.errorQueryBlank.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                showToast(getString(R.string.query_none))
            }
        })

        vm.errorFailSearch.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                showToast(getString(R.string.movie_search_fail))
            }
        })

        vm.resultEmpty.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (vm.resultEmpty.get() as Boolean) {
                    showToast(getString(R.string.movie_not_found))
                }
            }
        })

        vm.searchResultList.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                vm.searchResultList.get()?.let { adapter.setItems(it) }
            }
        })
    }

    private fun showToast(toast: String) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show()
    }
}

