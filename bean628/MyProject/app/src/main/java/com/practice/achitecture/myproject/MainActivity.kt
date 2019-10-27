package com.practice.achitecture.myproject

import android.os.Bundle
import android.view.View
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

        mRetrofitClient = RetrofitClient()

        rv_searched_list.layoutManager = LinearLayoutManager(this)
    }

    override fun onClick(v: View?) {

        val word: String = input_search_sth?.text.toString()

        when (v?.id) {
            R.id.btn_search -> search(word, mSearchType)
            R.id.btn_search_type_movie ->
                search(word, 0)
            R.id.btn_search_type_book ->
                search(word, 1)
            R.id.btn_search_type_blog ->
                search(word, 2)
            R.id.btn_search_type_news ->
                search(word, 3)
        }

    }

    private fun search(word: String, searchType: Int) {
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
                }
            }

            override fun onFailure(call: Call<ResultOfSearchingModel>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

}
