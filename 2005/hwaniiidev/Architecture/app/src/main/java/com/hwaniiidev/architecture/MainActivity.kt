package com.hwaniiidev.architecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.LayoutDirection
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hwaniiidev.architecture.Model.Item
import com.hwaniiidev.architecture.Model.ResponseMovieSearchData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName
    val clientId = "KXY8b7w9cuaFFHkDSGwS"
    val clientPw = "HdI9WbTqtt"
    val displayValue = 30

    var movieList : List<Item>? = null
    lateinit var adapterMovieList : AdapterMovieList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = APICall.setUp()

        initView()

        btn_search.setOnClickListener {
            val searchValue = edit_search_title.text.toString()
            api.getMovieSearchData(clientId,clientPw,searchValue,displayValue).enqueue(object : retrofit2.Callback<ResponseMovieSearchData>{
                override fun onResponse(
                    call: Call<ResponseMovieSearchData>,
                    response: Response<ResponseMovieSearchData>
                ) {
                    Log.d(TAG,response.body()!!.lastBuildDate)
                    Log.d(TAG,"total : ${response.body()!!.total}")
                    Log.d(TAG,"start : ${response.body()!!.start}")
                    Log.d(TAG,"display : ${response.body()!!.display}")
                    for(it in response.body()!!.items){
                        Log.d(TAG,"title : ${it.title}")
                    }

                    movieList = response.body()!!.items
                    listUpdate()
                    text_plz_search.visibility = View.GONE

                }

                override fun onFailure(call: Call<ResponseMovieSearchData>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }

    }
    fun initView(){
        recyclerview_search_list.layoutManager = LinearLayoutManager(this)

    }

    fun listUpdate(){
        adapterMovieList = AdapterMovieList(this, this!!.movieList)
        recyclerview_search_list.adapter = adapterMovieList
        adapterMovieList.notifyDataSetChanged()
    }
}
