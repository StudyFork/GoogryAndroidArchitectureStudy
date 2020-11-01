package kr.dktsudgg.androidarchitecturestudy

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kr.dktsudgg.androidarchitecturestudy.api.WebRequestManager
import kr.dktsudgg.androidarchitecturestudy.api.naver.NaverMovieApi
import kr.dktsudgg.androidarchitecturestudy.api.naver.data.MovieItem
import kr.dktsudgg.androidarchitecturestudy.api.naver.data.NaverMovieResponse
import kr.dktsudgg.androidarchitecturestudy.view.adapter.MovieListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * 영화 검색결과 리스트 보여주는 RecyclerView 매니저 등록
         */
        searchedMovieList.layoutManager = LinearLayoutManager(this);

        /**
         * 검색 버튼 OnClicklistener 등록
         */
        movieSearchBtn.setOnClickListener(this)
    }

    /**
     * 클릭 이벤트 처리 메소드
     */
    override fun onClick(clickedView: View?) {
        when (clickedView?.id) {
            R.id.movieSearchBtn -> {    // 검색 버튼 클릭 시, 검색어 입력한 내용을 가지고 검색 수행
                Log.i("버튼 클릭", "검색 버튼 클릭")
                val searchText = movieSearchEditText.text.toString()
                searchMovie(this, searchText)
            }
            else -> {
            }
        }
    }

    /**
     * 영화 검색 메소드
     */
    fun searchMovie(context: Context, searchText: String) {
        WebRequestManager.getInstance(NaverMovieApi::class)?.searchMovies(
            getString(R.string.naver_client_id),
            getString(R.string.naver_client_secret),
            searchText,
            null,
            null
        )?.enqueue(object : Callback<NaverMovieResponse> {
            override fun onResponse(
                call: Call<NaverMovieResponse>,
                response: Response<NaverMovieResponse>
            ) {
                if (response.isSuccessful) {
                    Log.i("네이버 영화 API 요청 성공", "${response.raw()}")

                    /**
                     * RecyclerView 갱신
                     */
                    refreshSearchedMovieList(context, response.body())
                }
            }

            override fun onFailure(call: Call<NaverMovieResponse>, t: Throwable) {
                Log.e("네이버 영화 API 요청 실패", "${t}")
                Toast.makeText(context, "검색 요청에 실패하였습니다.", Toast.LENGTH_SHORT).show()

                /**
                 * RecyclerView 갱신
                 */
                refreshSearchedMovieList(context, null)
            }
        })
    }

    /**
     * 영화 검색 결과 갱신 메소드
     */
    fun refreshSearchedMovieList(context: Context, data: NaverMovieResponse?) {
        data?.items?.let {
            searchedMovieList.adapter = MovieListAdapter(
                context,
                data.items as MutableList<MovieItem>
            )
        }
    }

}