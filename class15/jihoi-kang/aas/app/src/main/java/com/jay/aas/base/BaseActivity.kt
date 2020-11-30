package com.jay.aas.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.jay.aas.BR
import com.jay.aas.api.RetrofitHelper
import com.jay.aas.data.MovieLocalDataSourceImpl
import com.jay.aas.data.MovieRemoteDataSourceImpl
import com.jay.aas.data.MovieRepository
import com.jay.aas.data.MovieRepositoryImpl
import com.jay.aas.room.AppDatabase
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<B : ViewDataBinding, VM : ViewModel>(
    @LayoutRes private val layoutResId: Int,
) : AppCompatActivity() {

    protected lateinit var binding: B

    protected val viewModel: VM by lazy {
        ((javaClass.genericSuperclass as ParameterizedType?)
            ?.actualTypeArguments
            ?.get(1) as Class<VM>)
            .getDeclaredConstructor(MovieRepository::class.java)
            .newInstance(movieRepository)
    }

    private val movieRepository: MovieRepository by lazy {
        val remoteDataSource = MovieRemoteDataSourceImpl(RetrofitHelper.movieService)
        val appDatabase = AppDatabase.getInstance(this)
        val localDataSource = MovieLocalDataSourceImpl(
            appDatabase.movieDao(),
            appDatabase.searchHistoryDao()
        )
        MovieRepositoryImpl(remoteDataSource, localDataSource)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        with(binding) {
            lifecycleOwner = this@BaseActivity
            setVariable(BR.vm, viewModel)
        }
    }

}