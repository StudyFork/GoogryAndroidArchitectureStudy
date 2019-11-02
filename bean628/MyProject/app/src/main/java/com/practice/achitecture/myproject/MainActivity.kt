package com.practice.achitecture.myproject

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.practice.achitecture.myproject.model.ResultOfSearchingModel
import com.practice.achitecture.myproject.network.RetrofitClient
import common.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var searchType: Int = SEARCH_TYPE_MOVIE
    private lateinit var retrofitClient: RetrofitClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_search.setOnClickListener(this)
        btn_search_type_movie.setOnClickListener(this)
        btn_search_type_book.setOnClickListener(this)
        btn_search_type_blog.setOnClickListener(this)
        btn_search_type_news.setOnClickListener(this)
        input_search_sth.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    search(searchType)
                    true
                }
                else -> false
            }
        }

        retrofitClient = RetrofitClient(NAVER_API_BASE_URL)

    }

    override fun onClick(v: View) {

        when (v.id) {
            R.id.btn_search -> search(searchType)
            R.id.btn_search_type_movie ->
                search(SEARCH_TYPE_MOVIE)
            R.id.btn_search_type_book ->
                search(SEARCH_TYPE_BOOK)
            R.id.btn_search_type_blog ->
                search(SEARCH_TYPE_BLOG)
            R.id.btn_search_type_news ->
                search(SEARCH_TYPE_NEWS)
        }

    }

    private fun search(searchType: Int) {
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(input_search_sth.windowToken, 0)

        val word: String = input_search_sth?.text.toString()
        if (word.isEmpty()) {
            Toast.makeText(
                this@MainActivity,
                getString(R.string.toast_empty_word),
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        this.searchType = searchType
        val category = when (searchType) {
            SEARCH_TYPE_MOVIE -> "movie"
            SEARCH_TYPE_BOOK -> "book"
            SEARCH_TYPE_BLOG -> "blog"
            SEARCH_TYPE_NEWS -> "news"
            else -> "movie"
        }

        val call: Call<ResultOfSearchingModel> =
            retrofitClient.retrofitInterface.searchSomething(category, word)

        call.enqueue(object : Callback<ResultOfSearchingModel> {
            override fun onResponse(
                call: Call<ResultOfSearchingModel>,
                response: Response<ResultOfSearchingModel>
            ) {
                response.body()?.let {
                    if (response.isSuccessful) {
                        rv_searched_list.adapter = when (this@MainActivity.searchType) {
                            SEARCH_TYPE_MOVIE, SEARCH_TYPE_BOOK -> SearchMovieAndBookAdapter(it.searchedItems)
                            SEARCH_TYPE_BLOG, SEARCH_TYPE_NEWS -> SearchBlogAndNewsAdapter(it.searchedItems)
                            else -> SearchMovieAndBookAdapter(it.searchedItems)
                        }

                        if (rv_searched_list.adapter?.itemCount == 0) {
                            Toast.makeText(
                                this@MainActivity,
                                getString(R.string.toast_empty_result),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResultOfSearchingModel>, t: Throwable) {

            }
        })
    }

}
