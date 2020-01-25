package com.example.architecturestudy.ui.image

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturestudy.Injection
import com.example.architecturestudy.R
import com.example.architecturestudy.data.model.ImageItem
import kotlinx.android.synthetic.main.fragment_image.*

class ImageFragment : Fragment(), ImageContract.View {

    private lateinit var imageAdapter: ImageAdapter

    private val presenter : ImageContract.Presenter by lazy {
        ImagePresenter(
            view = this,
            repository = context?.let { Injection.provideNaverSearchRepository(it) }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageAdapter = ImageAdapter()

        recycleview.apply {
            adapter = imageAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        }

        presenter.getLastData()

        btn_search.setOnClickListener {
            if(input_text != null) {
                val edit = edit_text.text.toString()
                presenter.apply {
                    val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
                    val activeNetwork: NetworkInfo? = cm?.activeNetworkInfo
                    val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

                    taskSearch(
                        isNetWork = isConnected,
                        keyword = edit
                    )
                }
            }
        }
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this.activity, message, Toast.LENGTH_SHORT)
    }

    override fun showEmpty(message: String) {
        Toast.makeText(this.activity, message, Toast.LENGTH_SHORT)
    }

    override fun showResult(item: List<ImageItem>) {
        imageAdapter.update(item)
    }
}