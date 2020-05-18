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
import android.widget.*
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_main_top.*
import kotlinx.android.synthetic.main.row_content.view.*
import r.test.rapp.data.model.Item
import r.test.rapp.networks.NaverApi
import r.test.rapp.networks.RetrofitClient
import r.test.rapp.data.model.MovieVo
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

        lv_contents.adapter = MovieAdapter()
        lv_contents.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->

                val webIntent: Intent =
                    Uri.parse((lv_contents.adapter as MovieAdapter).movieList[position].link)
                        .let { webpage ->
                            Intent(Intent.ACTION_VIEW, webpage)
                        }

                startActivity(webIntent)

            }

        showKeyPad(edt_input)
    }

    /**
     * 키패드 숨기기
     */
    private fun hideKeyPad(v: View) {
        val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
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
        val api: NaverApi =
            RetrofitClient.getClient(BuildConfig.NAVER_API_URL).create(NaverApi::class.java)

        val keyword: String = edt_input.text.toString().trim()

        if (TextUtils.isEmpty(keyword)) {
            Toast.makeText(this@MainActivity, R.string.enter_keyword, Toast.LENGTH_LONG).show()
            return
        }

        hideKeyPad(edt_input)

        progress?.show()

        api.searchMovie(keyword).enqueue(object : Callback<MovieVo> {
            override fun onFailure(call: Call<MovieVo>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_LONG).show()
                progress?.hide()
            }

            override fun onResponse(call: Call<MovieVo>, response: Response<MovieVo>) {
                val body = response.body() ?: return

//                if (response.body() == null)
//                    return;

                val adt = lv_contents.adapter as MovieAdapter
                adt.movieList.clear()
                adt.movieList.addAll(body.items)
                adt.notifyDataSetChanged()

                progress?.hide()
            }
        }
        )
    }

    /**
     * 리스트 뷰의 커스텀 아답터.
     */
    class MovieAdapter : BaseAdapter() {
        val movieList: ArrayList<Item> = ArrayList()
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val inflater: LayoutInflater = LayoutInflater.from(parent?.context)
            val rowView: View = convertView ?: inflater.inflate(R.layout.row_content, parent, false)
//            val rowView: View = if (convertView == null) {
//                inflater.inflate(R.layout.row_content, parent, false)
//            } else {
//                convertView
//            }

            val holder = (convertView?.tag as? ViewHolder)
                ?: run {
                    ViewHolder(rowView).also { rowView.tag = it }
                }

//            var holder: ViewHolder? = null;
//
//            if (convertView == null) {
//                holder = ViewHolder(rowView)
//                rowView.tag = holder;
//            } else {
//                holder = rowView.tag as ViewHolder
//            }

            val item = movieList[position]

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.txtTitle.text = Html.fromHtml(item.title, Html.FROM_HTML_MODE_COMPACT)
            } else {
                holder.txtTitle.text = Html.fromHtml(item.title)
            }
            Glide.with(rowView.context)
                .load(item.image)
                .placeholder(R.drawable.no_image)
                .centerCrop()
                .into(holder.ivThumbnail)

            return rowView
        }

        private class ViewHolder(view: View) {
            val txtTitle: TextView = view.txt_title
            val ivThumbnail: ImageView = view.iv_thumbnail

        }

        override fun getItem(position: Int): Item {
            return movieList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return movieList.size
        }
    }
}
