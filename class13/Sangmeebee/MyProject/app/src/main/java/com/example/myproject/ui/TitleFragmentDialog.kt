package com.example.myproject.ui

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.myproject.R
import com.example.myproject.databinding.DialogFragmentTitleBinding

class TitleFragmentDialog : DialogFragment(), OnListItemSelectedInterface {

    private var titleList: ArrayList<String> = arrayListOf()
    private lateinit var onListItemSelectedInterface: OnListItemSelectedInterface
    private lateinit var binding: DialogFragmentTitleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { titleList = it.getStringArrayList("title_list") as ArrayList<String> }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_title, container, false)?.apply {
            binding = DataBindingUtil.bind(this)!!
            binding.dialog = this@TitleFragmentDialog
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TitleAdapter(this)
        adapter.setTitleList(titleList)
        binding.rvSearchList.adapter = adapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            onListItemSelectedInterface = activity as OnListItemSelectedInterface
        } catch (e: ClassCastException) {
            Log.e("sangmee", "onAttach: " + e.message)
        }
    }

    override fun onItemSelected(title: String) {
        onListItemSelectedInterface.onItemSelected(title)
        dismiss()
    }

    //다이얼로그 크기 지정
    override fun onResume() {
        super.onResume()
        val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val deviceWidth = size.x
        params?.width = (deviceWidth * 0.9).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }

    companion object {
        fun newInstance(titleList: ArrayList<String>): TitleFragmentDialog =
            TitleFragmentDialog().apply {
                arguments = Bundle().apply {
                    putStringArrayList("title_list", titleList)
                }
            }
    }
}
