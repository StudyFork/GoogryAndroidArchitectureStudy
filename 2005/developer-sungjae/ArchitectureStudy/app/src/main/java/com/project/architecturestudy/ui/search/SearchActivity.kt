package com.project.architecturestudy.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import com.project.architecturestudy.R
import com.project.architecturestudy.data.repository.NaverMovieRepositoryImpl
import com.project.architecturestudy.data.source.local.NaverMovieLocalDataSourceImpl
import com.project.architecturestudy.data.source.remote.NaverMovieRemoteDataSourceImpl
import com.project.architecturestudy.databinding.ActivitySearchBinding
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.toast

class SearchActivity : AppCompatActivity() {

    private val adapter: SearchAdapter = SearchAdapter()
    private val handler by lazy {
        Handler(Looper.getMainLooper())
    }

    private val vm by lazy {
        val naverMovieLocalDataSource = NaverMovieLocalDataSourceImpl(this)
        val naverMovieRemoteDataSource = NaverMovieRemoteDataSourceImpl()
        val repository = NaverMovieRepositoryImpl(naverMovieLocalDataSource, naverMovieRemoteDataSource)
        SearchViewModel(repository)
    }
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        binding.vm = vm

        setRecyclerView()
        onClickAdapterItem()

        et_search.doAfterTextChanged {
            vm.invokeTextChanged()
        }
        vm.showToast.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val msg = getString(vm.showToast.get() ?: return)
                handler.postDelayed({ toast(msg) }, 0)
            }
        })


    }

    private fun onClickAdapterItem() {
        adapter.onClick = { item ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
            startActivity(intent)
        }
    }

    private fun setRecyclerView() {
        listview_movie.adapter = adapter
    }

    override fun onDestroy() {
        vm.remoteDispose()
        super.onDestroy()
    }
}

//        //ViewModel
//        val showToast = ObservableField<Unit>()
//
//        //Activity
//        vm.showToast.addOnPropertyChangedCallback(object :
//            OnPropertyChangedCallback() {
//            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
//                toast("")
//            }
//        })
//
//        // viewModel
//        showToast.notifyChange()
