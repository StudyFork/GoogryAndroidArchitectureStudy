package com.cnm.homework.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.cnm.homework.R
import com.cnm.homework.adapter.MovieAdapter
import com.cnm.homework.data.model.NaverResponse
import com.cnm.homework.databinding.ActivityMainBinding
import com.cnm.homework.extension.hideKeyboard
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val movieAdapter = MovieAdapter(::showMovieDetail)
    private val viewModel: MainViewModel by viewModel()
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity()
        changedCallback()
    }

    private fun initActivity() {
        with(binding) {
            rvContent.adapter = movieAdapter
            lifecycleOwner = this@MainActivity
            this.vm = viewModel

        }

        if (movieAdapter.movieItems.isEmpty()) {
            val r = Runnable { beforeMovieListSearch() }
            val thread = Thread(r)
            thread.start()
        }
    }

    private fun changedCallback() {
        viewModel.isKeyboardBoolean.observe(this@MainActivity, Observer {
            if (!it) binding.etMovieSearch.hideKeyboard()
        })

        viewModel.toastString.observe(this@MainActivity, Observer {
            showToast(it)
        })
    }

    private fun beforeMovieListSearch() {
        val repoItem = viewModel.loadMovieList()
        runOnUiThread {
            viewModel.setItems(repoItem)
        }
    }

    private fun showMovieDetail(item: NaverResponse.Item) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
        startActivity(intent)
    }

    private fun showToast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
