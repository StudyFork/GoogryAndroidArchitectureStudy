package com.hansung.firstproject.ui.main

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hansung.firstproject.R
import com.hansung.firstproject.adapter.MovieItemAdapter
import com.hansung.remote.data.ErrorStringResource
import com.hansung.firstproject.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var movieItemAdapter: MovieItemAdapter = MovieItemAdapter()
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        with(binding) {
            vm = mainViewModel
            lifecycleOwner = this@MainActivity
        }

        // recyclerView initialize
        initRecyclerView()
        // keyboard function
        setKeyboardSearchFunc()
        initObserveCallback()
        R.string.empty_keyword_message.toString()
    }

    // recyclerView 초기화 메소드
    private fun initRecyclerView() {
        with(binding.recyclerViewMovies) {
            adapter = movieItemAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL
                )
            )
        }
    }

    //키보드 제거 메소드
    private fun removeKeyboard() =
        (this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            binding.etSearch.windowToken,
            0
        )

    private fun showErrorKeywordEmpty() {
        Toast.makeText(this, getString(R.string.empty_keyword_message), Toast.LENGTH_SHORT).show()
    }

    private fun showErrorByErrorMessage(errorMessage: String) {
        Toast.makeText(this, ErrorStringResource.valueOf(errorMessage).resId, Toast.LENGTH_SHORT)
            .show()
    }

    private fun showErrorEmptyList() {
        Toast.makeText(this, getString(R.string.empty_list_message), Toast.LENGTH_SHORT).show()
    }

    private fun setKeyboardSearchFunc() {
        binding.etSearch.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent?): Boolean {
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        mainViewModel.doSearch()
                    }
                    else ->
                        return false
                }
                return true
            }
        })
    }

    private fun initObserveCallback() {
        with(mainViewModel) {
            showError.run {
                observe(this@MainActivity, Observer {
                    showErrorByErrorMessage(it.toString())
                })
            }

            isEmptyResult.observe(this@MainActivity, Observer { showErrorEmptyList() })

            showKeywordEmptyError.observe(this@MainActivity, Observer { showErrorKeywordEmpty() })

            hideKeyboard.observe(this@MainActivity, Observer { removeKeyboard() })
        }
    }
}