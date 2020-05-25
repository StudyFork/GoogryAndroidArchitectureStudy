package r.test.rapp.main

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_main_top.*
import r.test.rapp.R
import r.test.rapp.data.model.Item

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), Contractor.View {

    private var progress: ProgressDialog? = null
    private var presenter: Contractor.Presenter = PresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

        presenter.setView(this)
        showKeyPad()
    }

    /**
     * 검색버튼 클릭 액션
     */
    fun onClick(view: View) {
        val keyword: String = edt_input.text.toString().trim()
        presenter.searchData(keyword)
        hideKeyPad()
    }

    override fun showToast(msg: String) {
        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_LONG).show()
    }

    override fun showToast(resId: Int) {
        showToast(getString(resId))
    }

    override fun showProgress() {
        progress?.show()
    }

    override fun hideProgress() {
        progress?.hide()
    }

    override fun hideKeyPad() {
        val imm: InputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(edt_input.windowToken, 0)
    }

    override fun showKeyPad() {
        edt_input.requestFocus()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    }

    override fun refreshListView(items: List<Item>) {
        val adt = lv_contents.adapter as MovieAdapter
        val movieList = adt.getMovieList();
        movieList.clear()
        movieList.addAll(items)
        adt.notifyDataSetChanged()
    }

    private fun initView() {
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
        lv_contents.onItemClickListener = OnItemClickListenerImpl()
    }

    private class OnItemClickListenerImpl : AdapterView.OnItemClickListener {

        private var adt: MovieAdapter? = null

        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            adt = adt ?: parent.adapter as MovieAdapter

            val uri: Uri = Uri.parse(adt?.getMovieList()!![position].link)
            parent.context.startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }
}