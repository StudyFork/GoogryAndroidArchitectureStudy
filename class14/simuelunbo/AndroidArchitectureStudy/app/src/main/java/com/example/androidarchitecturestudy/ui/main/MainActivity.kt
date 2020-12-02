package com.example.androidarchitecturestudy.ui.main

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.Observable
import com.example.androidarchitecturestudy.R
import com.example.androidarchitecturestudy.data.local.NaverLocalDataSourceImpl
import com.example.androidarchitecturestudy.data.remote.NaverRemoteDataSourceImpl
import com.example.androidarchitecturestudy.data.repository.NaverRepositoryImpl
import com.example.androidarchitecturestudy.databinding.ActivityMainBinding
import com.example.androidarchitecturestudy.link
import com.example.androidarchitecturestudy.ui.MovieAdapter
import com.example.androidarchitecturestudy.ui.TitleFragmentDialog
import com.example.androidarchitecturestudy.ui.base.BaseActivity

class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by lazy {
        val remoteMovieDataImpl = NaverRemoteDataSourceImpl()
        val localMovieDataImpl = NaverLocalDataSourceImpl()
        val repositoryMovieImpl = NaverRepositoryImpl(remoteMovieDataImpl, localMovieDataImpl)
        MainViewModel(repositoryMovieImpl)
    }

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter { link ->
            link(this, link)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        initRecyclerView()
        setViewModelCallBack()
        viewModel.requestLocalMovieData()
    }

    private fun initRecyclerView() {
        binding.rcvResult.adapter = movieAdapter
    }

    private fun setViewModelCallBack() {
        viewModel.msg.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Toast.makeText(this@MainActivity, viewModel.msg.get(), Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.keyboard.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                    binding.etSearch.windowToken,
                    0
                )
            }
        })

        viewModel.dialog.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                TitleFragmentDialog().show(supportFragmentManager, "title_history")

            }
        })

    }

}