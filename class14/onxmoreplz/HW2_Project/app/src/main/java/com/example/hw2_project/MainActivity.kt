package com.example.hw2_project

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.hw2_project.data.repository.MovieRepositoryImpl

class MainActivity : AppCompatActivity() {

    private lateinit var editTextMovieName : EditText
    private lateinit var buttonSearch : Button
    private lateinit var recyclerView : RecyclerView

    //어댑터 선언
    private val adapter = RecyclerViewAdapter()

    //레퍼지토리 선언
    private val movieRepositoryImp = MovieRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSearch = findViewById<Button>(R.id.button_search)
        editTextMovieName = findViewById<EditText>(R.id.edittext_movie_name)
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        //리사이클러뷰 동일한 크기의 아이템 사용
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        presenter = Presenter(this, movieRepositoryImp)
        buttonSearch.setOnClickListener {

            //사용자가 EditTextView에 아무값도 넣지 않았을 때
            if (editTextMovieName.text.isEmpty()) {
                Toast.makeText(this, "영화 제목을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            movieRepositoryImp.getMovieList(
                editTextMovieName.text.toString(),
                success = {
                    runOnUiThread{
                        adapter.movieListChange(it.items)
                    }
                },
                fail = { Log.e("getMovieList","Error in movieRepositoryImp")}
            )

            //키보드 내리기
            val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(editTextMovieName.windowToken, 0)
        }
    }

}