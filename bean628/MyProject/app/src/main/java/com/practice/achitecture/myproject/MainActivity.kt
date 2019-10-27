package com.practice.achitecture.myproject

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.practice.achitecture.myproject.model.ResultOfSearchingModel
import com.practice.achitecture.myproject.network.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var mSearchType: Int = 0    // 0: 영화, 1: 책, 2: 블로그, 3: 뉴스
    private lateinit var mRetrofitClient: RetrofitClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_search.setOnClickListener(this)
        btn_search_type_movie.setOnClickListener(this)
        btn_search_type_book.setOnClickListener(this)
        btn_search_type_blog.setOnClickListener(this)
        btn_search_type_news.setOnClickListener(this)
        input_search_sth.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                when (actionId) {
                    EditorInfo.IME_ACTION_SEARCH -> {
                        search(mSearchType)
                        return true
                    }
                    else -> return false
                }
            }
        })

        mRetrofitClient = RetrofitClient()

        rv_searched_list.layoutManager = LinearLayoutManager(this)
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.btn_search -> search(mSearchType)
            R.id.btn_search_type_movie ->
                search(0)
            R.id.btn_search_type_book ->
                search(1)
            R.id.btn_search_type_blog ->
                search(2)
            R.id.btn_search_type_news ->
                search(3)
        }

    }

    private fun search(searchType: Int) {
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(input_search_sth.windowToken, 0)

        val word: String = input_search_sth?.text.toString()
        if (word.isEmpty()) {
            Toast.makeText(this@MainActivity, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        mSearchType = searchType

        val call: Call<ResultOfSearchingModel> = when (mSearchType) {
            0 -> mRetrofitClient.retrofitInterface.searchMovie(word)
            1 -> mRetrofitClient.retrofitInterface.searchBook(word)
            2 -> mRetrofitClient.retrofitInterface.searchBlog(word)
            3 -> mRetrofitClient.retrofitInterface.searchNews(word)
            else -> mRetrofitClient.retrofitInterface.searchMovie(word)
        }

        call.enqueue(object : Callback<ResultOfSearchingModel> {
            override fun onResponse(
                call: Call<ResultOfSearchingModel>,
                response: Response<ResultOfSearchingModel>
            ) {
                if (response.code() == 200) {
                    rv_searched_list.adapter = when (mSearchType) {
                        0, 1 -> SearchMovieAndBookAdapter(response.body()?.items ?: ArrayList())
                        2, 3 -> SearchBlogAndNewsAdapter(response.body()?.items ?: ArrayList())
                        else -> SearchMovieAndBookAdapter(response.body()?.items ?: ArrayList())
                    }

                    if (rv_searched_list.adapter!!.itemCount == 0) {
                        Toast.makeText(this@MainActivity, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call<ResultOfSearchingModel>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

}
