package com.hwaniiidev.architecture

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hwaniiidev.architecture.model.Item
import com.hwaniiidev.architecture.model.ResponseMovieSearchData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName
    val clientId = "KXY8b7w9cuaFFHkDSGwS"
    val clientPw = "HdI9WbTqtt"
    val displayValue = 30
    lateinit var adapterMovieList: AdapterMovieList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        btn_search.setOnClickListener {
            val searchValue = edit_search_title.text.toString()

            if (edit_search_title.text.isEmpty()) {       //검색어 입력하지 않았을 때
                Log.d(TAG, "없음")
                Toast.makeText(this, "검색어를 입력해주세요.", Toast.LENGTH_LONG).show()
            } else {
                ApiCall.api.getMovieSearchData(clientId, clientPw, searchValue, displayValue)
                    .enqueue(object : retrofit2.Callback<ResponseMovieSearchData> {
                        override fun onResponse(
                            call: retrofit2.Call<ResponseMovieSearchData>,
                            response: Response<ResponseMovieSearchData>
                        ) {
                            if (response.isSuccessful) {
                                Log.d(TAG, response.body()!!.lastBuildDate)
                                Log.d(TAG, "total : ${response.body()!!.total}")
                                Log.d(TAG, "start : ${response.body()!!.start}")
                                Log.d(TAG, "display : ${response.body()!!.display}")
                                for (it in response.body()!!.items) {
                                    Log.d(TAG, "title : ${it.title}")
                                }

                                if (response.body()!!.total == 0) {
                                    text_plz_search.text = "검색결과가 없습니다.\n다른 검색어을 입력해주세요."
                                    text_plz_search.visibility = View.VISIBLE
                                } else {
                                    listUpdate(response.body()!!.items)
                                    text_plz_search.visibility = View.GONE
                                }

                            } else {
                                Log.d(TAG, "APICall failed");
                                Toast.makeText(
                                    this@MainActivity,
                                    "다른 검색어를 입력해주세요.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                        override fun onFailure(
                            call: retrofit2.Call<ResponseMovieSearchData>,
                            t: Throwable
                        ) {
                            Log.d(TAG, "APICall failed : " + t.message);
                            Toast.makeText(
                                this@MainActivity,
                                "네트워크에 연결할 수 없습니다. 네트워크 연결을 확인해주세요.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    })
            }
        }
    }

    fun initView() {
        recyclerview_search_list.layoutManager = LinearLayoutManager(this)
        adapterMovieList = AdapterMovieList()
        recyclerview_search_list.adapter = adapterMovieList
    }

    fun listUpdate(items: List<Item>) {
        adapterMovieList.addItem(items)
    }
}
