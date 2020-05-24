package r.test.rapp.main

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
import r.test.rapp.R
import r.test.rapp.data.model.Item
import r.test.rapp.data.repository.MovieRepository
import r.test.rapp.data.repository.MovieRepositoryImpl
import r.test.rapp.networks.ImageLoader

class MainActivity : AppCompatActivity() {

    private var progress: ProgressDialog? = null
    private var repository: MovieRepository? = null


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
                    Uri.parse((lv_contents.adapter as MovieAdapter).getMovieList()[position].link)
                        .let { link ->
                            Intent(Intent.ACTION_VIEW, link)
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
        val keyword: String = edt_input.text.toString().trim()

        if (TextUtils.isEmpty(keyword)) {
            Toast.makeText(this@MainActivity, R.string.enter_keyword, Toast.LENGTH_LONG).show()
            return
        }

        hideKeyPad(edt_input)
        progress?.show()

        repository = repository ?: MovieRepositoryImpl()
        repository?.getMovieList(
            keyword,
            onSuccess = { vo ->
                val res = vo ?: return@getMovieList
                val adt = lv_contents.adapter as MovieAdapter
                val movieList = adt.getMovieList();
                movieList.clear()
                movieList.addAll(res.items)
                adt.notifyDataSetChanged()

                progress?.hide()
            },
            onFail = { f ->
                Toast.makeText(this@MainActivity, f.toString(), Toast.LENGTH_LONG).show()
                progress?.hide()
            });
    }

    /**
     * 리스트 뷰의 커스텀 아답터.
     */
    class MovieAdapter : BaseAdapter() {
        private val movieList: ArrayList<Item> = ArrayList()
        private val imgLoader : ImageLoader = ImageLoader()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val inflater: LayoutInflater = LayoutInflater.from(parent?.context)
            val rowView: View = convertView ?: inflater.inflate(R.layout.row_content, parent, false)

            val holder = (convertView?.tag as? ViewHolder)
                ?: run {
                    ViewHolder(rowView)
                        .also { rowView.tag = it }
                }

            val item = movieList[position]

            holder.txtTitle.text = item.getHtmlTitle()
            imgLoader.load(item.image, holder.ivThumbnail)

            return rowView
        }

        fun getMovieList() : ArrayList<Item>{
            return movieList
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
