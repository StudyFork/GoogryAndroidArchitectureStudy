package com.wybh.androidarchitecturestudy

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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

    private lateinit var cinemaAdapter: CinemaAdapter
    private lateinit var imm: InputMethodManager

    private var itemList: ArrayList<CinemaItem> = ArrayList()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initAdapter()
        initViewClickListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    private fun initView() {
        etSearchWord = findViewById(R.id.et_searchWord)
        btnSearch = findViewById(R.id.btn_search)
        rvCinema = findViewById(R.id.rv_cinema)

        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }
    
    private fun initAdapter() {
        cinemaAdapter = CinemaAdapter(itemList)
        rvCinema.run {
            adapter = cinemaAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
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
                        // 리스트 초기화
                        itemList.clear()

                        response.items.forEach{
                            val item = CinemaItem(it.image, it.title, it.actor, it.userRating, it.pubDate, it.link)
                            itemList.add(item)
                        }

                        // adapter에 리스트 전달 및 교체
                        cinemaAdapter.setList(itemList)

                        // 키보드 내리기
                        imm.hideSoftInputFromWindow(etSearchWord.windowToken, 0)
                    }, { error: Throwable ->
                        Log.d("jsh", "fail >>> " + error.message)
                    }))
        }
    }


}
