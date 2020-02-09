package com.studyfork.architecturestudy.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.Observable
import androidx.recyclerview.widget.LinearLayoutManager
import com.studyfork.architecturestudy.R
import com.studyfork.architecturestudy.base.BaseActivityV2
import com.studyfork.architecturestudy.databinding.ActivityMainBinding
import com.studyfork.architecturestudy.extension.hideKeyboard
import com.studyfork.architecturestudy.ui.adapter.MovieResultRVAdapter

class MainActivity : BaseActivityV2<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    private val movieResultRVAdapter: MovieResultRVAdapter by lazy {
        MovieResultRVAdapter {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
        }
    }

    override val viewModel: MainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this@MainActivity
        setMovieRecyclerView()
        setObservableCallback()
    }

    private fun setObservableCallback() {
        with(viewModel) {
            isErrorEmptyQuery.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    showErrorEmptyQuery()
                }
            })

            isErrorEmptyResult.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    showErrorEmptyResult()
                }
            })
        }
    }

    private fun showErrorEmptyQuery() {
        Toast.makeText(baseContext, getString(R.string.empty_query_notice), Toast.LENGTH_SHORT)
            .show()
    }

    private fun showErrorEmptyResult() {
        Toast.makeText(baseContext, getString(R.string.empty_data_notice), Toast.LENGTH_SHORT)
            .show()
    }

    private fun setMovieRecyclerView() {
        binding.rvMovieList.run {
            layoutManager = LinearLayoutManager(baseContext)
            adapter = movieResultRVAdapter
        }
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_search -> {
                currentFocus?.hideKeyboard()
                viewModel.getMovieList()
            }
        }
    }
}
