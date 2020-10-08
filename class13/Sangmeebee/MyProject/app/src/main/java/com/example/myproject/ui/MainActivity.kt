package com.example.myproject.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.R
import com.example.myproject.data.model.Items
import com.example.myproject.databinding.ActivityMainBinding
import com.example.myproject.extension.toast
import com.example.myproject.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val movieAdapter = MovieAdapter()
    private lateinit var binding: ActivityMainBinding
    val vm = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = vm

        setRecyclerView()
        viewModelCallback()

    }

    private fun setRecyclerView() {
        binding.recyclerview.apply {
            setHasFixedSize(true)
            adapter = movieAdapter
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }
    }

    private fun viewModelCallback(){
        vm.showToastMsg.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                vm.msg.get()?.let {
                    when (it) {
                        "success" -> toast(R.string.call_success)
                        "empty" -> toast(R.string.call_empty)
                        "empty_result" -> toast(R.string.call_result_empty)
                        "error" -> toast(R.string.call_error)
                    }
                }
            }
        })

        vm.movieList.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                movieAdapter.clearAndAddItems(vm.movieList.get() as ArrayList<Items>)
            }
        })

        vm.showDialog.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                showTitleDialog()
            }
        })
    }

    fun showTitleDialog() {
        val titleDialog = TitleFragmentDialog()
        titleDialog.mainViewModel = vm
        titleDialog.show(supportFragmentManager, "title_history_dialog")

        vm.isVisible.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                titleDialog.dismiss()
            }
        })
    }
}
