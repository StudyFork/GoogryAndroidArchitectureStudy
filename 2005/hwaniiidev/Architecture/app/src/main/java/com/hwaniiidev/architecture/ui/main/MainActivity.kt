package com.hwaniiidev.architecture.ui.main

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.hwaniiidev.architecture.R
import com.hwaniiidev.architecture.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    val mainViewModel: MainViewModel by inject()

    lateinit private var imm: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            this.viewModel = mainViewModel
            lifecycleOwner = this@MainActivity
        }

        mainViewModel.error.observe(this, Observer {
            when (it) {
                SearchError.QUERY_IS_NONE,
                SearchError.RESPONSE_ERROR,
                SearchError.NETWORK_FAILURE -> {
                    toast(it.errorMessage)
                }
            }
        })

        mainViewModel.hideKeyboard.observe(this, Observer {
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
