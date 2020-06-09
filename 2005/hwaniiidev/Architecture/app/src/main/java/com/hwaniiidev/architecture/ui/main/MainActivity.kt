package com.hwaniiidev.architecture.ui.main

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import com.hwaniiidev.architecture.R
import com.hwaniiidev.architecture.data.repository.NaverMovieRepositoryImpl
import com.hwaniiidev.architecture.databinding.ActivityMainBinding
import com.hwaniiidev.architecture.ui.main.MainViewModel.Companion.ERROR_NETWORK_FAILURE
import com.hwaniiidev.architecture.ui.main.MainViewModel.Companion.ERROR_QUERY_IS_NONE
import com.hwaniiidev.architecture.ui.main.MainViewModel.Companion.ERROR_RESPONSE_ERROR
import com.hwaniiidev.architecture.ui.main.MainViewModel.Companion.ERROR_RESPONSE_IS_NONE
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    private val TAG = MainActivity::class.java.simpleName

    //lateinit var adapterMovieList: AdapterMovieList

    private val naverMovieRepositoryImpl = NaverMovieRepositoryImpl(this)
    //private val mainPresenter = MainPresenter(this, naverMovieRepositoryImpl)

    lateinit private var imm: InputMethodManager
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = MainViewModel(naverMovieRepositoryImpl)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //binding.mainActivity = this
        binding.viewModel = viewModel
        viewModel.error.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                when(viewModel.error.get()){
                    ERROR_QUERY_IS_NONE -> showQueryIsEmpty()
                    ERROR_RESPONSE_IS_NONE -> showResponseIsNone()
                    ERROR_RESPONSE_ERROR -> showResponseError()
                    ERROR_NETWORK_FAILURE -> showNetworkFailure()
                }
            }

        })
        viewModel.hideKeyboard.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                hideKeyBoard()
            }

        })
        //initView()

        imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager


    }

    /*fun searchMovies(searchValue : String){
        mainPresenter.searchMovies(searchValue)
    }*/

    /*private fun initView() {
        adapterMovieList = AdapterMovieList()
        recyclerview_search_list.adapter = adapterMovieList
    }*/

    private fun toast(message: String) {
        Toast.makeText(
            this@MainActivity,
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    fun showQueryIsEmpty() {
        toast("검색어를 입력해주세요.")
        Log.d(TAG, "없음")
    }

    fun hideKeyBoard() {
        imm.hideSoftInputFromWindow(edit_search_title.windowToken, 0)
    }

    fun showResponseIsNone() {
        text_plz_search.text = "검색결과가 없습니다.\n다른 검색어을 입력해주세요."
        //binding.movieList = null
    }

    fun showResponseError() {
        toast("다시 시도해주세요.")
    }

    fun showNetworkFailure() {
        toast("네트워크에 연결할 수 없습니다. 네트워크 연결을 확인해주세요.")
    }

    /*fun showMoviesList(items: List<Item>) {
        runOnUiThread {
            adapterMovieList.addItem(items)
            binding.movieList = items
        }
    }*/


}
