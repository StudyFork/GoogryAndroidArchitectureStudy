package com.cnm.homework.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
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
    private val vm: MainViewModel by lazy { MainViewModel(localDao) }
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity()
        changedCallback()
    }

    override fun onDestroy() {
        vm.disposableClear()
        super.onDestroy()
    }

    private fun initActivity() {
        binding.rvContent.adapter = movieAdapter
        binding.vm = vm
        if (movieAdapter.movieItems.isEmpty()) {
            val r = Runnable { beforeMovieListSearch() }
            val thread = Thread(r)
            thread.start()
        }
    }

    private fun changedCallback() {
        vm.isKeyboardBoolean.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                vm.isKeyboardBoolean.get()?.let {
                    if (!it) binding.etMovieSearch.hideKeyboard()
                }
            }
        })
        vm.toastString.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                vm.toastString.get()?.let { showToast(it) }
            }
        })
    }

    private fun beforeMovieListSearch() {
        val repoItem = vm.loadMovieList()
        runOnUiThread {
            vm.setItems(repoItem)
        }
    }

    private fun showMovieDetail(item: NaverResponse.Item) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
        startActivity(intent)
    }

    fun showToast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
