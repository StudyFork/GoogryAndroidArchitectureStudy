package com.example.androidarchitecturestudy.ui.main

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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

    private val viewModel by viewModels<MainViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val remoteMovieDataImpl = NaverRemoteDataSourceImpl()
                val localMovieDataImpl = NaverLocalDataSourceImpl()
                val repositoryMovieImpl =
                    NaverRepositoryImpl(remoteMovieDataImpl, localMovieDataImpl)
                return MainViewModel(repositoryMovieImpl) as T
            }
        }
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

        viewModel.msg.observe(this) {
            showToastMsg(viewModel.msg.value!!)
        }

        viewModel.keyboard.observe(this) {
            (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
                binding.etSearch.windowToken,
                0
            )
        }

        viewModel.showDialog.observe(this) {
            TitleFragmentDialog().show(supportFragmentManager, "title_history")
        }

        viewModel.hideDialog.observe(this) {
            supportFragmentManager.findFragmentByTag("title_history")?.let {
                (it as DialogFragment).dismiss()
            }
        }
    }

}