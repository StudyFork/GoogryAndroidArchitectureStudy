package r.test.rapp

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_main_top.*
import kotlinx.android.synthetic.main.row_content.view.*
import r.test.rapp.networks.NaverApi
import r.test.rapp.networks.RetrofitClient
import r.test.rapp.vo.Item
import r.test.rapp.vo.MovieVo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    /**
     * Deprecated 됐지만 구글은 해당 클래스를 아직까지 살려둠. 사실 커스텀 뷰 만들기 귀찮아서 사용...
     */
    var progress: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress = ProgressDialog(this)
        lv_contents.emptyView = txt_empty
        edt_input.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                onClick(v)
                true
            } else {
                false
            }
        }

        showKeyPad(edt_input)
    }

    /**
     * 키패드 숨기기
     */
    private fun hideKeyPad(v: View) {
        var imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)

    }

    /**
     * 키패드 보여주기.
     */
    private fun showKeyPad(v: EditText) {
        v.requestFocus()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }

    /**
     * 검색버튼 클릭 액션
     */
    fun onClick(view: View) {
        var api: NaverApi = RetrofitClient.getClient(BuildConfig.NAVER_API_URL).create(NaverApi::class.java)

        var keyword: String = edt_input.text.toString()

        if (TextUtils.isEmpty(keyword)) {
            Toast.makeText(this@MainActivity, R.string.enter_keyword, Toast.LENGTH_LONG).show()
            return
        }

        hideKeyPad(edt_input)

        progress?.show()

        keyword = keyword.trim()

        api.searchMovie(keyword).enqueue(object : Callback<MovieVo> {
            override fun onFailure(call: Call<MovieVo>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_LONG).show()
                progress?.hide()
            }

            override fun onResponse(call: Call<MovieVo>, response: Response<MovieVo>) {
                var adt = response.body()?.let { MovieAdapter(response.body()!!.items) }
                lv_contents.adapter = adt
                lv_contents.onItemClickListener =
                    AdapterView.OnItemClickListener { parent, view, position, id ->

                        val webIntent: Intent =
                            Uri.parse(response.body()!!.items[position].link).let { webpage ->
                                Intent(Intent.ACTION_VIEW, webpage)
                            }

                        startActivity(webIntent)

                    }
                progress?.hide()
            }
        }
        )
    }

    /**
     * 리스트 뷰의 커스텀 아답터.
     */
    class MovieAdapter(var items: List<Item>) : BaseAdapter() {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var inflater: LayoutInflater = LayoutInflater.from(parent?.context)
            var rowView = inflater.inflate(R.layout.row_content, parent, false)
            var item = items[position]

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                rowView.txt_title.text = Html.fromHtml(item.title, Html.FROM_HTML_MODE_COMPACT)
            } else {
                rowView.txt_title.text = Html.fromHtml(item.title)
            }
            Glide.with(rowView.context).load(item.image).placeholder(R.drawable.no_image)
                .centerCrop().into(rowView.iv_thumbnail)

            return rowView
        }

        override fun getItem(position: Int): Item {
            return items[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return items.size
        }
    }
}
