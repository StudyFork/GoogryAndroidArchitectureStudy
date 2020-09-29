package com.hong.architecturestudy.ui.main

import android.os.Bundle
import androidx.databinding.Observable
import com.hong.architecturestudy.R
import com.hong.architecturestudy.data.repository.RepositoryDataSourceImpl
import com.hong.architecturestudy.data.source.local.LocalDataSourceImpl
import com.hong.architecturestudy.data.source.remote.RemoteDataSourceImpl
import com.hong.architecturestudy.databinding.ActivityMainBinding
import com.hong.architecturestudy.ext.toast
import com.hong.architecturestudy.ui.base.BaseActivity
import com.hong.architecturestudy.ui.moviedialog.MovieListDialogFragment

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val vm by lazy {
        MainViewModel(RepositoryDataSourceImpl(LocalDataSourceImpl(), RemoteDataSourceImpl()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBinding()
        showToastMessage()
    }

    private fun setBinding() {
        val fragment = MovieListDialogFragment.getInstance()
        fragment.mainViewModel = vm

        binding.apply {
            vm = this@MainActivity.vm
            fragmentDialog = fragment
            manager = supportFragmentManager
        }
    }

    private fun showToastMessage() {
        vm.msg.apply {
            addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    when (get()) {
                        Message.NETWORK_ERROR -> toast(R.string.message_network_error)
                        Message.SUCCESS -> toast(R.string.message_success)
                    }
                }
            })
        }
    }
}