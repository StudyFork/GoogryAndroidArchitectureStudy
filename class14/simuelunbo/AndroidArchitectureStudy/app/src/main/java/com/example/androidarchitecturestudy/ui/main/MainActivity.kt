package com.example.androidarchitecturestudy.ui.main

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.fragment.app.DialogFragment
import com.example.androidarchitecturestudy.R
import com.example.androidarchitecturestudy.databinding.ActivityMainBinding
import com.example.androidarchitecturestudy.link
import com.example.androidarchitecturestudy.ui.MovieAdapter
import com.example.androidarchitecturestudy.ui.TitleFragmentDialog
import com.example.androidarchitecturestudy.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()

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