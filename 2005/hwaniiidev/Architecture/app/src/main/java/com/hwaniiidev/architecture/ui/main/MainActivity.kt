package com.hwaniiidev.architecture.ui.main

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hwaniiidev.architecture.R
import com.hwaniiidev.architecture.data.repository.NaverMovieRepositoryImpl
import com.hwaniiidev.architecture.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private val naverMovieRepositoryImpl = NaverMovieRepositoryImpl(this)

    lateinit private var imm: InputMethodManager
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelProvider = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(naverMovieRepositoryImpl) as T
            }
        })
        val viewModel = viewModelProvider[MainViewModel::class.java]

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            this.viewModel = viewModel
            lifecycleOwner = this@MainActivity
        }

        viewModel.error.observe(this, Observer {
            when (it) {
                SearchError.QUERY_IS_NONE,
                SearchError.RESPONSE_ERROR,
                SearchError.NETWORK_FAILURE -> {
                    toast(it.errorMessage)
                }
            }
        })

        viewModel.hideKeyboard.observe(this, Observer {
            hideKeyBoard()
        })

        imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    }

    private fun toast(message: String) {
        Toast.makeText(
            this@MainActivity,
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    fun hideKeyBoard() {
        imm.hideSoftInputFromWindow(edit_search_title.windowToken, 0)
    }
}
