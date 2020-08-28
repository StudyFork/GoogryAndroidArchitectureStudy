package com.camai.archtecherstudy

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.camai.archtecherstudy.adapter.MovieSearchAdapter
import com.camai.archtecherstudy.model.Items
import com.camai.archtecherstudy.model.MovieResponseModel
import com.camai.archtecherstudy.network.MovieApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG = "MovieSearch"
    private lateinit var movieSearchAdapter: MovieSearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //  Recycler View And Adapter Init
        setAdapterAndRecyclerViewInit()

        //  Search Button Click Event
        btn_search.setOnClickListener(View.OnClickListener {
            hideKeyboard(this)
            progressbar.isVisible = true
            searchStart()
        })

    }

    //  RecyclerView Adapter Set
    private fun setAdapterAndRecyclerViewInit() {
        movieSearchAdapter = MovieSearchAdapter(this)
        recycler_view.run {
            adapter = movieSearchAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(false)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        }
    }

    //  Hardware Keyboard hide
    private fun hideKeyboard(context: Context) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(edit_name.windowToken, 0)
    }

    //  Keyword Empty Toast
    private fun showEmptyFieldText() {
        Toast.makeText(applicationContext, "검색어를 입력해주세요.", Toast.LENGTH_LONG).show()
    }

    //  Keyword Error Toast
    private fun showNotFoundMessage(keyword: String) {
        Toast.makeText(applicationContext, keyword + " 를 찾을 수 없습니다.", Toast.LENGTH_LONG).show()
    }

    //  Movie Name Search
    private fun searchStart() {
        val moviename: String = edit_name.text.toString()

        //  Movie Name Value Check
        if (moviename.isBlank() && moviename.isNullOrEmpty()) {
            showEmptyFieldText()
        } else {
            recycler_view.layoutManager?.scrollToPosition(0)
            getMoiveSearchCall(moviename)
        }
    }

    //  Update Movie Search Result Data List
    private fun setListData(infoList: ArrayList<Items>) {
        movieSearchAdapter.setClearAndAddList(infoList)

        progressbar.isVisible = false
        edit_name.text.clear()

    }

    //  Naver Moive Search Api Call
    private fun getMoiveSearchCall(movietitle: String) {

        MovieApiService.create().getMovieSearch(movietitle, 100, 1).enqueue(object :
            retrofit2.Callback<MovieResponseModel> {

            override fun onResponse(
                call: Call<MovieResponseModel>,
                response: Response<MovieResponseModel>
            ) {
                // Success
                if (response.isSuccessful) {

                    val body = response.body()
                    body?.let {
                        runOnUiThread {
                            setListData(it.items)
                        }
                    }

                } else {
                    Log.e(TAG, response.message())
                }
            }

            override fun onFailure(call: Call<MovieResponseModel>, t: Throwable) {
                // Failed
                showNotFoundMessage(movietitle)
                Log.e(TAG, t.message.toString())
            }
        })
    }
}