package com.onit.googlearchitecturestudy.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.onit.googlearchitecturestudy.R
import com.onit.googlearchitecturestudy.databinding.ActivityMainBinding
import com.onit.googlearchitecturestudy.ui.movieInformation.MovieInformationActivity
import com.onit.googlearchitecturestudy.ui.movieInformation.MovieInformationActivity.Companion.MOVIE_URL
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

typealias ClickMovieListener = (Int) -> Unit

class MainActivity : AppCompatActivity() {

    private lateinit var resultMovieListRecyclerAdapter: ResultMovieListRecyclerAdapter
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)!!
        binding.viewModel = mainViewModel
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
        with(mainViewModel) {
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
