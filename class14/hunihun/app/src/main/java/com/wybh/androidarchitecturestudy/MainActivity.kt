package com.wybh.androidarchitecturestudy

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.wybh.androidarchitecturestudy.model.ResponseCinemaData
import com.wybh.androidarchitecturestudy.retrofit.RetrofitCreator
import com.wybh.androidarchitecturestudy.retrofit.RetrofitImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {
    private lateinit var etSearchWord: EditText
    private lateinit var btnSearch: Button
    private lateinit var rvCinema: RecyclerView
    private lateinit var imm: InputMethodManager

    private val compositeDisposable = CompositeDisposable()
    private val cinemaAdapter: CinemaAdapter = CinemaAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initAdapter()
        initViewClickListener()
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

    private fun initView() {
        etSearchWord = findViewById(R.id.et_searchWord)
        btnSearch = findViewById(R.id.btn_search)
        rvCinema = findViewById(R.id.rv_cinema)

        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }
    
    private fun initAdapter() {
        rvCinema.run {
            adapter = cinemaAdapter
        }
    }

    private fun initViewClickListener() {
        btnSearch.setOnClickListener {
            compositeDisposable.add(
                RetrofitCreator.create(RetrofitImpl::class.java)
                    .getCinemaData(etSearchWord.text.toString())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribe({ response: ResponseCinemaData ->
                        // adapter list clear
                        cinemaAdapter.dataClear()
                        
                        response.items.map{
                            val item = CinemaItem(it.image, it.title, it.actor, it.userRating, it.pubDate, it.link)
                            cinemaAdapter.addItem(item)
                        }

                        // recyclerview 갱신
                        cinemaAdapter.notifyDataSetChanged()

                        // 키보드 내리기
                        imm.hideSoftInputFromWindow(etSearchWord.windowToken, 0)
                    }, { error: Throwable ->
                        Log.d("jsh", "fail >>> " + error.message)
                    }))
        }
    }
}
