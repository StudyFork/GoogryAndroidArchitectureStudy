package com.example.myproject.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.R
import com.example.myproject.databinding.ActivityMainBinding
import com.example.myproject.extension.toast
import com.example.myproject.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val movieAdapter = MovieAdapter()
    private lateinit var binding: ActivityMainBinding
    private val vm by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = vm
        binding.lifecycleOwner = this

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

    private fun viewModelCallback() {
        vm.msg.observe(this, Observer {
            Log.d("sangmin", "hi")
            when (it) {
                "success" -> toast(R.string.call_success)
                "empty" -> toast(R.string.call_empty)
                "empty_result" -> toast(R.string.call_result_empty)
                "error" -> toast(R.string.call_error)
            }
        })

        vm.movieList.observe(this, Observer {
            vm.movieList.value?.let { movieAdapter.clearAndAddItems(it) }
        })

        vm.showDialog.observe(this, Observer {
            TitleFragmentDialog().show(supportFragmentManager, "title_history_dialog")
        })
    }
}
