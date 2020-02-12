package com.example.study.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil.setContentView
import androidx.databinding.Observable
import com.example.study.R
import com.example.study.data.model.Movie
import com.example.study.data.repository.NaverSearchRepositoryImpl
import com.example.study.data.source.local.NaverSearchLocalDataSourceImpl
import com.example.study.data.source.local.SearchResultDatabase
import com.example.study.data.source.remote.NaverSearchRemoteDataSourceImpl
import com.example.study.databinding.ActivityMainBinding
import com.example.study.ui.adapter.MovieAdapter
import com.example.study.ui.detail.DetailActivity
import com.example.study.util.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val vm: MainViewModel by lazy {
        MainViewModel(
            NaverSearchRepositoryImpl.getInstance(
                NaverSearchLocalDataSourceImpl.getInstance(SearchResultDatabase.getInstance(this)!!.searchResultDao())
                , NaverSearchRemoteDataSourceImpl.getInstance()
            )
        )
    }

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.rvMovieList.adapter = movieAdapter
        getRecentSearchResult()
        addObserveProperty()
    }

    private fun getRecentSearchResult() {
        vm.getRecentSearchResult()
    }

    private fun showErrorQueryEmpty() {
        Toast.makeText(applicationContext, R.string.empty_query_message, Toast.LENGTH_SHORT).show()
    }

    private fun showErrorEmptyResult() {
        Toast.makeText(applicationContext, R.string.empty_result_message, Toast.LENGTH_SHORT).show()
    }

    private fun failSearch() {
        Toast.makeText(applicationContext, R.string.fail_search, Toast.LENGTH_SHORT).show()
    }

    private fun addObserveProperty() {

        vm.errorQueryEmpty.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                showErrorQueryEmpty()
            }
        })

        vm.errorFailSearch.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                failSearch()
            }
        })

        vm.errorResultEmpty.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                showErrorEmptyResult()
            }
        })

        vm.isKeyboardBoolean.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                vm.isKeyboardBoolean.get()?.let {
                    if (!it) {
                        hideKeyboard()
                    }
                }
            }
        })
    }

}




