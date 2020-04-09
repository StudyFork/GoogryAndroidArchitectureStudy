package com.byiryu.study.ui.mvvm.main.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.byiryu.study.databinding.ActivityMainBinding
import com.byiryu.study.model.data.MovieItem
import com.byiryu.study.ui.mvvm.base.views.BaseActivity
import com.byiryu.study.ui.mvvm.main.viewmodel.MainViewModel
import com.byiryu.study.ui.mvvm.main.views.adapter.MainRecyclerAdapter
import com.byiryu.study.wigets.OnViewClickListener

class MainActivity : BaseActivity(){

    private val adapter = MainRecyclerAdapter(
        onViewClickListener = object : OnViewClickListener{
            override fun onclick(data: Any) {
                if(data !is MovieItem){
                    return
                }
                goActivity(Intent(Intent.ACTION_VIEW, Uri.parse(data.link)))
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this@MainActivity, getBRApplication().viewModelFactory)[MainViewModel::class.java]

        val binding = ActivityMainBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@MainActivity
            vm = viewModel
            adapter = this@MainActivity.adapter.apply {
                lifecycleOwner = this@MainActivity
            }

            viewModel.apply {
                status.observe(this@MainActivity, Observer {
                    it?.run {
                        if (!first) {
                            when (val str = second) {
                                is Int -> showMsg(str)
                                is String -> showMsg(str)
                            }
                        } else {
                            return@run
                        }
                    }
                })

                movieData.observe(this@MainActivity, Observer {
                    this@MainActivity.adapter.submitList(it)
                })
            }
        }

        setContentView(binding.root)
    }
}