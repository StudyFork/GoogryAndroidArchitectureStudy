package com.onit.googlearchitecturestudy.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onit.googlearchitecturestudy.R
import com.onit.googlearchitecturestudy.databinding.ActivityMainBinding
import com.onit.googlearchitecturestudy.ui.movieInformation.MovieInformationActivity
import com.onit.googlearchitecturestudy.ui.movieInformation.MovieInformationActivity.Companion.MOVIE_URL
import kotlinx.android.synthetic.main.activity_main.*

typealias ClickMovieListener = (Int) -> Unit

class MainActivity : AppCompatActivity() {

    private lateinit var resultMovieListRecyclerAdapter: ResultMovieListRecyclerAdapter
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel() as T
            }
        })[MainViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)!!
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        init()
    }

    fun hideKeyBoard() {
        val view = this.currentFocus
        view?.let { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    fun showToastMessage(message: String, option: Int) {
        Toast.makeText(applicationContext, message, option).show()
    }

    private fun init() {
        setRecyclerView()
        setViewModelCallback()
    }

    private fun setRecyclerView() {
        resultMovieListRecyclerAdapter = ResultMovieListRecyclerAdapter(clickMovieListener)
        resultMovieListRecyclerView.adapter = resultMovieListRecyclerAdapter
    }

    private fun setViewModelCallback() {
        with(viewModel) {
            toastMessage.observe(this@MainActivity, Observer {
                showToastMessage(toastMessage.value ?: "", Toast.LENGTH_SHORT)
            })

            hideKeyBoard.observe(this@MainActivity, Observer {
                hideKeyBoard()
            })
        }
    }

    private val clickMovieListener: ClickMovieListener = { position ->
        Intent(this, MovieInformationActivity::class.java).apply {
            putExtra(MOVIE_URL, resultMovieListRecyclerAdapter.getMovieURL(position))
        }.run { startActivity(this) }
    }
}
