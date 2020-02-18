package com.example.study.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStoreOwner
import com.example.study.R
import com.example.study.data.model.Movie
import com.example.study.data.repository.NaverSearchRepositoryImpl
import com.example.study.data.source.local.NaverSearchLocalDataSourceImpl
import com.example.study.data.source.local.SearchResultDatabase
import com.example.study.data.source.remote.NaverSearchRemoteDataSourceImpl
import com.example.study.databinding.ActivityMainBinding
import com.example.study.ui.adapter.MovieAdapter
import com.example.study.util.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter()
    }

    /*    override val vm: MainViewModel by lazy {
            ViewModelProvider(this, object: ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return MainViewModel(NaverSearchRepositoryImpl.getInstance(
                        NaverSearchLocalDataSourceImpl.getInstance(SearchResultDatabase.getInstance(applicationContext)!!.searchResultDao())
                        , NaverSearchRemoteDataSourceImpl.getInstance()
                    )) as T
                }
            }).get(MainViewModel::class.java)
        }*/
    override val vm: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(
                    NaverSearchRepositoryImpl.getInstance(
                        NaverSearchLocalDataSourceImpl.getInstance(
                            SearchResultDatabase.getInstance(
                                applicationContext
                            )!!.searchResultDao()
                        )
                        , NaverSearchRemoteDataSourceImpl.getInstance()
                    )
                ) as T
            }
        }
    } // ktx 사용


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.rvMovieList.adapter = movieAdapter
        binding.lifecycleOwner = this
        getRecentSearchResult()
        addObserve()

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

    private fun addObserve() {

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




