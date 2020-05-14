package com.tsdev.tsandroid.ui

import android.os.Bundle
import android.widget.Toast
import com.tsdev.tsandroid.R
import com.tsdev.tsandroid.base.BaseActivity
import com.tsdev.tsandroid.constant.Const
import com.tsdev.tsandroid.data.repository.NaverReopsitory
import com.tsdev.tsandroid.data.repository.NaverRepositoryImpl
import com.tsdev.tsandroid.databinding.ActivityMainBinding
import com.tsdev.tsandroid.ext.showToast
import com.tsdev.tsandroid.provider.ResourceProviderImpl
import com.tsdev.tsandroid.ui.adapter.MovieRecyclerAdapter
import com.tsdev.tsandroid.ui.observe.ObserverProviderImpl
import com.tsdev.tsandroid.ui.viewmodel.MainViewModel
import com.tsdev.tsandroid.util.MapConverter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val binding: ActivityMainBinding by movieSetDataBinding(R.layout.activity_main, this)
    {
        it.also {
            it.activity = this
            it.viewModel = viewModel
        }
    }

    private val movieRecyclerAdapter: MovieRecyclerAdapter by lazy {
        MovieRecyclerAdapter()
    }
    private val movieMapConverter: MapConverter by lazy {
        MapConverter()
    }
    private val naverRepository: NaverReopsitory by lazy {
        NaverRepositoryImpl(movieMapConverter)
    }

    override val viewModel: MainViewModel by lazy {
        MainViewModel(
            naverRepository,
            ResourceProviderImpl(this),
            ObserverProviderImpl(),
            movieRecyclerAdapter,
            rxJavaEvent
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        disposable.add(
            rxJavaEvent.getBackButtonEvent()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .buffer(2, 1)
                .map { it[0] to it[1] }
                .subscribe({
                    if (it.second - it.first > Const.BACK_BUTTON_THROTTLE_TIME)
                        showToast(R.string.destroy_view_toast_message, Toast.LENGTH_LONG)
                    else
                        finish()
                }, {
                    it.printStackTrace()
                })
        )

        binding.movieRecycler.run {
            adapter = movieRecyclerAdapter
        }
    }

    override fun onBackPressed() {
        viewModel.onBackKeyPressed()
    }
}
