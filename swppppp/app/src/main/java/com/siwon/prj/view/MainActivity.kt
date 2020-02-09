package com.siwon.prj.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.siwon.prj.R
import com.siwon.prj.common.adapter.MovieAdapter
import com.siwon.prj.common.base.BaseActivity
import com.siwon.prj.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    lateinit var vm: MainViewmodel

    lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewmodel() as T
            }
        })[MainViewmodel::class.java]
        binding.vm = vm
        binding.lifecycleOwner = this
        adapter = MovieAdapter { link: String ->
            itemClick(link)
        }
        movieListRv.adapter = adapter

        vm.isKeyboardVisible.observe(this, Observer { isVisible ->
            if (!isVisible) hideKeyboard()
        })
        vm.toastMsg.observe(this, Observer {
            showToast(it)
        })
        binding.editTextInput.setOnClickListener {
            vm.isKeyboardVisible.value = true
        }
    }

    fun itemClick(link: String) {
        val detailWebview = Intent(this@MainActivity, DetailWebview::class.java)
        detailWebview.putExtra("link", link)
        startActivity(detailWebview)
    }

    fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        currentFocus?.let {
            imm?.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}
