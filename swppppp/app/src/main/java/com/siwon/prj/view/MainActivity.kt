package com.siwon.prj.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.Observable
import com.siwon.prj.R
import com.siwon.prj.common.adapter.MovieAdapter
import com.siwon.prj.common.base.BaseActivity
import com.siwon.prj.common.model.Movie
import com.siwon.prj.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main)/*, MainContract.View*/ {

//    private val present: MainContract.Presenter = MainPresenter(this)
//    private val present: MainContract.Presenter by inject{ parametersOf(this)}

    // viewmodel적용
    private val vm: MainViewmodel by lazy { MainViewmodel() }

    lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = vm
        adapter = MovieAdapter { link: String ->
            itemClick(link)
        }
        movieListRv.adapter = adapter

        vm.isKeyboardVisible.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                vm.isKeyboardVisible.get()?.let { isVisible ->
                    if (!isVisible) hideKeyboard()
                }
            }
        })
        vm.toastMsg.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                vm.toastMsg.get()?.let { showToast(it) }
            }
        })

        // 검색버튼
        binding.searchBtn.setOnClickListener {
            vm.searchMovie(edit_text_input.text.toString())
        }

        binding.editTextInput.setOnClickListener {
            vm.isKeyboardVisible.set(true)
        }
    }

//    override fun showResult(result: ArrayList<Movie>) {
//        with(binding) {
//            movieListRv.visibility = View.VISIBLE
//            emptyResultImg.visibility = View.GONE
//        }
//        with(adapter) {
//            clearItemList()
//            setItem(result)
//        }
//    }
//
//    override fun emptyInput() {
//        adapter.clearItemList()
//        showToast("입력값이 없습니다.")
//    }
//
//    override fun emptyResult() {
//        with(binding) {
//            movieListRv.visibility = View.GONE
//            emptyResultImg.visibility = View.VISIBLE
//        }
//        showToast("검색 결과가 없습니다.")
//    }
//
//    override fun serviceErr(erMsg: String) = showToast("${erMsg}\n\n 서비스 에러입니다. 네트워크를 확인해 주세요.")
//
//    override fun hideKeyboard() {
//        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
//        currentFocus?.let {
//            imm?.hideSoftInputFromWindow(it.windowToken, 0)
//        }
//    }
//
//    fun getMovies(input: String) = present.getSearchResult(input)

    fun itemClick(link: String) {
        val detailWebview = Intent(this@MainActivity, DetailWebview::class.java)
        detailWebview.putExtra("link", link)
        startActivity(detailWebview)
    }
    fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        currentFocus?.let {
            imm?.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}
