package com.siwon.prj.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.databinding.Observable
import com.siwon.prj.R
import com.siwon.prj.common.adapter.MovieAdapter
import com.siwon.prj.common.base.BaseActivity
import com.siwon.prj.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    // viewmodel적용
    private val vm: MainViewmodel by lazy { MainViewmodel() }

    lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = vm
        adapter = MovieAdapter { link: String ->
            itemClick(link)
        }
        movieListRv.adapter = adapter

        vm.isKeyboardVisible.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                vm.isKeyboardVisible.get()?.let { isVisible ->
                    if (!isVisible) hideKeyboard()
                }
            }
        })
        vm.toastMsg.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                vm.toastMsg.get()?.let { showToast(it) }
            }
        })

        binding.editTextInput.setOnClickListener {
            vm.isKeyboardVisible.set(true)
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
