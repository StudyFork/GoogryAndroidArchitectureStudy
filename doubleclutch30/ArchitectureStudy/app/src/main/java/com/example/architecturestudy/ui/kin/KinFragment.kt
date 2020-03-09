package com.example.architecturestudy.ui.kin

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.architecturestudy.Injection
import com.example.architecturestudy.R
import com.example.architecturestudy.data.model.KinItem
import com.example.architecturestudy.databinding.FragmentKinBinding

class KinFragment : Fragment(), KinContract.View {

    private lateinit var binding: FragmentKinBinding

    private lateinit var kinAdapter: KinAdapter

    private val presenter : KinContract.Presenter by lazy {
        KinPresenter(
            view = this,
            repository = context?.let { Injection.provideNaverSearchRepository(it) }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_kin,
            container,
            false
        )

        binding.view = this
        binding.presenter = presenter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        kinAdapter =  KinAdapter()

        presenter.getLastData()

        binding.recycleview.apply {
            adapter = kinAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(
                DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
            )
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this.activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun showEmpty(message: String) {
        Toast.makeText(this.activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun showResult(item: List<KinItem>) {
        kinAdapter.update(item)
    }
}
