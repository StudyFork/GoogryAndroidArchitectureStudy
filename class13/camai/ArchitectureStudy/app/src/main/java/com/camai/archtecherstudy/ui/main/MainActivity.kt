package com.camai.archtecherstudy.ui.main

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.camai.archtecherstudy.R
import com.camai.archtecherstudy.databinding.ActivityMainBinding
import com.camai.archtecherstudy.observer.MainViewModel
import com.camai.archtecherstudy.ui.rencentdialog.RecentMovieDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MovieSearch"

    private val vm: MainViewModel by lazy {
        MainViewModel()
    }

    private val movieSearchAdapter: MovieSearchAdapter by lazy {
        MovieSearchAdapter()
    }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = vm

        setAdapterAndRecyclerViewInit()

        setupObserverCallBack()
    }

    //  RecyclerView Adapter Set
    private fun setAdapterAndRecyclerViewInit() {

        //  recyclerView init
        binding.recyclerView.run {
            adapter = movieSearchAdapter
            setHasFixedSize(false)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

    }

    //  ViewModel CallBack
    private fun setupObserverCallBack() {

        vm.searchMovie.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                hideKeyboard()
            }
        })

        vm.textNull.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                showFieldText("")
            }
        })

        vm.successSearch.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                movieSearchAdapter.setClearAndAddList(vm.movieList.get()!!)
            }
        })
        vm.failedSearch.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val keyword = vm.keyword.get()

                if (keyword != null) {
                    showFieldText(keyword)
                }
            }
        })

        vm.openDialog.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                RecentMovieDialog(keywork = {
                    //  Click movie name
                    vm.keyword.set(it)
                    vm.onClickSearch()
                })
                    .show(supportFragmentManager, RecentMovieDialog.TAG)
            }
        })
    }

    //  Hardware Keyboard hide
    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(edit_name.windowToken, 0)
    }

    //  Toast Message
    private fun showFieldText(text: String) {
        if (text.isNullOrBlank()) {
            Toast.makeText(applicationContext, "검색어를 입력해주세요.", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(applicationContext, text + " 를 찾을 수 없습니다.", Toast.LENGTH_LONG).show()
        }
    }
}