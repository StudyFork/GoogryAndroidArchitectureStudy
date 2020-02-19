package com.cnm.homework.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cnm.homework.R
import com.cnm.homework.adapter.MovieAdapter
import com.cnm.homework.data.model.NaverResponse
import com.cnm.homework.data.source.local.db.LocalDao
import com.cnm.homework.data.source.local.db.LocalDatabase
import com.cnm.homework.databinding.ActivityMainBinding
import com.cnm.homework.extension.hideKeyboard

class MainActivity : AppCompatActivity() {

    private val movieAdapter = MovieAdapter(::showMovieDetail)
    private val localDao: LocalDao by lazy {
        val db = LocalDatabase.getInstance(this)!!
        db.localDao()
    }
    private val viewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(localDao) as T
            }
        }
    }
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
