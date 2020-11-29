package com.showmiso.architecturestudy.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import com.showmiso.architecturestudy.Constants
import com.showmiso.architecturestudy.R
import com.showmiso.architecturestudy.data.local.LocalDataSourceImpl
import com.showmiso.architecturestudy.data.remote.RemoteDataSourceImpl
import com.showmiso.architecturestudy.data.repository.NaverRepositoryImpl
import com.showmiso.architecturestudy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val movieViewModel by lazy {
        MovieViewModel(
            naverRepository = run {
                val prefs = getSharedPreferences(Constants.PREF_HISTORY_KEY, Context.MODE_PRIVATE)
                val localDataSourceImpl = LocalDataSourceImpl(prefs)
                val remoteDataSourceImpl = RemoteDataSourceImpl()
                NaverRepositoryImpl(
                    remoteDataSource = remoteDataSourceImpl,
                    localDataSource = localDataSourceImpl
                )
            }
        )
    }
    private val adapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = movieViewModel

        initUi()
    }

    private fun initUi() {
        binding.rcvResult.adapter = adapter

        // update movieList
        movieViewModel.movieList.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                movieViewModel.movieList.get()?.let(adapter::setMovieList)
            }
        })

        movieViewModel.showDataIsEmpty.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.msg_no_result),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        movieViewModel.showThrowError.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Log.e(tag, "Failed", movieViewModel.showThrowError.get())
            }
        })

        movieViewModel.showProgress.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                binding.progressBar.visibility = View.VISIBLE
            }
        })

        movieViewModel.hideProgress.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    fun onClickHistory(view: View) {
        startActivityForResult(
            Intent(this@MainActivity, HistoryActivity::class.java),
            REQUEST_CODE_HISTORY
        )
    }

    override fun onDestroy() {
        movieViewModel.clearDisposable()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_HISTORY) {
            if (resultCode == Activity.RESULT_OK) {
                val result = data?.getStringExtra(Constants.INTENT_KEY_HISTORY)
                result?.let {
                    movieViewModel.searchMovie(it)
                    binding.etSearch.setText(it)
                }
            }
        }
    }

    companion object {
        private val tag = MainActivity::class.java.simpleName
        private const val REQUEST_CODE_HISTORY = 0x01
    }
}
