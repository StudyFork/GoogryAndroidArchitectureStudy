package kr.dktsudgg.androidarchitecturestudy

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.Observable
import kr.dktsudgg.androidarchitecturestudy.databinding.ActivityMainBinding
import kr.dktsudgg.androidarchitecturestudy.view.ui.mvvm.MovieSearchViewModel

class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main), View.OnClickListener {

    private val movieSearchViewModel by lazy { MovieSearchViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activity = this@MainActivity
        binding.vm = movieSearchViewModel

        /**
         * 영화 검색 시, 검색어를 입력하지 않았을 경우에 대한 처리 모음
         */
        movieSearchViewModel.eventWhenEmptyInputInjected.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Toast.makeText(this@MainActivity, "검색어를 입력하세요", Toast.LENGTH_LONG).show();
            }

        })

        /**
         * 영화 검색 성공 시에 대한 처리 모음
         */
        movieSearchViewModel.eventWhenDataRefreshRequestSuccess.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Toast.makeText(this@MainActivity, "검색에 성공하였습니다.", Toast.LENGTH_LONG).show();
            }

        })

        /**
         * 영화 검색 실패 시에 대한 처리 모음
         */
        movieSearchViewModel.eventWhenDataRefreshRequestFailure.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                Toast.makeText(this@MainActivity, "검색에 실패하였습니다.", Toast.LENGTH_LONG).show();
            }

        })

    }

    /**
     * 클릭 이벤트 처리 메소드
     */
    override fun onClick(clickedView: View) {
        when (clickedView.id) {
            R.id.movieSearchBtn -> {    // 검색 버튼 클릭 시, 검색어 입력한 내용을 가지고 검색 수행
                val searchText = binding.movieSearchEditText.text.toString()
                movieSearchViewModel.refreshSearchMovieList(searchText)
            }
            R.id.showMovieSearchHistoryBtn -> { // 최근검색이력 버튼 클릭 시, 영화검색이력을 보여주는 액티비티로 이동
                startActivityForResult(
                    Intent(this, MovieSearchHistoryActivity::class.java), ACTIVITY_REQ_CODE
                )
            }
            else -> {
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            ACTIVITY_REQ_CODE -> {  // 반환된 내용을 가지고 EditText에 세팅 및 검색버튼 클릭 수행
                if (resultCode == RESULT_OK) {
                    binding.movieSearchEditText.setText(
                        data?.getStringExtra("selectedKeyword") ?: ""
                    )
                    binding.movieSearchBtn.performClick()
                } else {
                    Toast.makeText(
                        this,
                        "MovieSearchHistoryActivity에서 데이터를 반환하지 않았습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            else -> {
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        private const val ACTIVITY_REQ_CODE = 12335
    }

}