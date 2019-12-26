package com.siwon.prj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val CLIENT_ID = "KjLtxBCCy8ZTWORQ7uas"
    private val CLIENT_SECRET = "PHBD6IlPgd"
    private val BASE_URL = "https://openapi.naver.com"
    lateinit var retrofit: Retrofit
    lateinit var apiService: MovieSearchService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiServiceSet()

        // 검색버튼
        search_btn.setOnClickListener {
            if (edit_text_input.text.isNullOrBlank()) {
                var toast = Toast.makeText(this, "검색어를 입력해 주세요", Toast.LENGTH_SHORT)
                toast.show()
            }else{
                var toast = Toast.makeText(this, "입력한 겁색어는 ${edit_text_input.text.toString()}", Toast.LENGTH_SHORT)
                toast.show()

                apiService.search(CLIENT_ID, CLIENT_SECRET, edit_text_input.text.toString()).enqueue(object : Callback<Movies>{
                    override fun onFailure(call: Call<Movies>, t: Throwable) {
                        Log.i("로그로그", "####실패메시지: ${t.message}")
                        var toast = Toast.makeText(this@MainActivity, "검색에 실패하였습니다.", Toast.LENGTH_SHORT)
                        toast.show()
                    }

                    override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                        Log.i("로그로그", "####응답: ${response.body().toString()}\n ${response.message()}")
                        val adapter = MovieListAdapter(response.body()!!.movies)
                        list.layoutManager = LinearLayoutManager(this@MainActivity)
                        list.adapter = adapter
                    }
                })
            }
        }

    }

    fun apiServiceSet() {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(MovieSearchService::class.java)
    }

    // 영화리스트 어댑터
    inner class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.movieHolder> {
        val items : ArrayList<Movie>

        constructor(items: ArrayList<Movie>) {
            this.items = items
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): movieHolder
            = movieHolder(LayoutInflater.from(this@MainActivity).inflate(R.layout.list_item, parent, false))

        override fun getItemCount(): Int = items.size

        override fun onBindViewHolder(holder: movieHolder, position: Int) {
            holder.bind(items[position])
            holder.itemView.setOnClickListener {
                val toast = Toast.makeText(this@MainActivity, "웹뷰로 이동", Toast.LENGTH_SHORT)
                toast.show()
                val detailWebview = Intent(this@MainActivity, DetailWebview::class.java)
                detailWebview.putExtra("link", items[position].link)
                startActivity(detailWebview)
            }
        }

        inner class movieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bind(item: Movie){
                Glide.with(itemView).load(item.image).into(itemView.findViewById(R.id.img_view))
                itemView.findViewById<TextView>(R.id.movie_name).text = item.title
                itemView.findViewById<RatingBar>(R.id.score).rating = item.userRating.toFloat()/2f
                itemView.findViewById<TextView>(R.id.pub_date).text = item.pubDate
                itemView.findViewById<TextView>(R.id.director).text = item.director
                itemView.findViewById<TextView>(R.id.actor).text = item.actor
            }
        }

    }
}
