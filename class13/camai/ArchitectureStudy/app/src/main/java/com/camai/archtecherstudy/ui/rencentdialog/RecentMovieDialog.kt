package com.camai.archtecherstudy.ui.rencentdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.camai.archtecherstudy.R
import com.camai.archtecherstudy.databinding.RecentMovieListPopupBinding
import com.camai.archtecherstudy.observer.MainViewModel

class RecentMovieDialog : DialogFragment() {


    private lateinit var mvm: MainViewModel

    private val recentMovieAdapter: RecentMovieAdapter by lazy {
        RecentMovieAdapter {
            //  recycler View item click movie name to Activity
            mvm.keyword.value = it
            dismiss()
            mvm.onClickSearch()
        }
    }

    private lateinit var binding: RecentMovieListPopupBinding

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.bind(
                inflater.inflate(
                    R.layout.recent_movie_list_popup,
                    container,
                    false
                )
            )!!
        mvm = ViewModelProvider(
            getViewModelStoreOwner(),
            MainViewModelFactory()
        )[MainViewModel::class.java]

        binding.lifecycleOwner = this
        binding.mvm = mvm
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapterAndRecyclerViewInit()

        setupObserverCallBack()

    }

    private fun Fragment.getViewModelStoreOwner(): ViewModelStoreOwner = try {
        requireActivity()
    } catch (e: IllegalStateException) {
        this
    }


    //  RecyclerView Adapter Set
    private fun setAdapterAndRecyclerViewInit() {

        recentMovieAdapter.notifyDataSetChanged()

        //  recycler view init and adapter connect
        binding.recyclerRecentView.run {
            adapter = recentMovieAdapter
            setHasFixedSize(false)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

        //  get Recent Data
        mvm.setRecentData()
    }

    private fun setupObserverCallBack() {
        mvm.isFailed.observe(viewLifecycleOwner, Observer<Unit> {
            Toast.makeText(requireContext(), "최근 검색된 항목이 없습니다.", Toast.LENGTH_LONG).show()
        })


        mvm.isSuccess.observe(viewLifecycleOwner, Observer<Unit> {
            recentMovieAdapter.setClearAndAddList(mvm.recentList.value!!)

        })

        mvm.closeDialog.observe(viewLifecycleOwner, Observer<Unit> {
            dismiss()
        })

    }


    companion object {
        const val TAG = "MovieSearchDialog"
    }
}