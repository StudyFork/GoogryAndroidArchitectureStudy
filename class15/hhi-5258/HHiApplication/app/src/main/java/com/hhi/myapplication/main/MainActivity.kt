package com.hhi.myapplication.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.databinding.Observable
import com.hhi.myapplication.R
import com.hhi.myapplication.base.BaseActivity
import com.hhi.myapplication.databinding.ActivityMainBinding
import com.hhi.myapplication.recentSearch.RecentSearchActivity
import com.hhi.myapplication.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val vm = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activity = this@MainActivity
        binding.vm = vm

        setObserver()
    }

    private fun setObserver() {
        vm.hideKeyBoardEvent.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val inputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
            }
        })

        vm.emptyQueryEvent.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                showToast("내용을 입력해 주세요")
            }
        })

        vm.searchRecentQueryEvent.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                startActivityForResult(
                    Intent(this@MainActivity, RecentSearchActivity::class.java),
                    RC_ACTIVITY_FOR_RESULT
                )
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                RC_ACTIVITY_FOR_RESULT -> vm.searchMovie(data!!.getStringExtra("query"))
            }
        }
    }

    companion object {
        private const val RC_ACTIVITY_FOR_RESULT: Int = 100
    }
}
