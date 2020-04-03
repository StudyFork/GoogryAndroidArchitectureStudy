package com.byiryu.study.ui.mvvm.main.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.databinding.Observable
import com.byiryu.study.databinding.ActivityMainBinding
import com.byiryu.study.model.data.MovieItem
import com.byiryu.study.ui.mvvm.base.views.BaseActivity
import com.byiryu.study.ui.mvvm.main.viewmodel.MainViewModel
import com.byiryu.study.ui.mvvm.main.views.adapter.MainRecyclerAdapter
import com.byiryu.study.wigets.OnViewClickListener

class MainActivity : BaseActivity(){

    private val viewModel by lazy {
        MainViewModel(getBRApplication().repository)
    }

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

        val binding = ActivityMainBinding.inflate(layoutInflater).apply {
            vm = viewModel
            adapter = this@MainActivity.adapter
            netStatus = viewModel.netStatus
        }

        viewModel.run {
            status.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    viewModel.status.get()?.run{
                        if(!first) {
                            when (val str = second) {
                                is Int -> showMsg(str)
                                is String -> showMsg(str)
                            }
                        }else{
                            return
                        }
                    }
                }
            })
            data.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    this@MainActivity.adapter.submitList(data.get())
                }
            })
        }

        setContentView(binding.root)
    }

    override fun onDestroy() {
        viewModel.onDestroy()
        super.onDestroy()
    }
}