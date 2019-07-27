package kr.schoolsharing.coinhelper.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.schoolsharing.coinhelper.R
import kr.schoolsharing.coinhelper.model.UpbitItem

class CoinFragment(val itemList: List<UpbitItem>) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_fragment_krw, container, false)
        val mRecyclerViewKRW = view.findViewById<RecyclerView>(R.id.mRecyclerView_KRW)

        val myRVAdapter = MainRvAdapter(context!!, itemList)
        mRecyclerViewKRW.adapter = myRVAdapter

        val lm = LinearLayoutManager(context!!)
        mRecyclerViewKRW.layoutManager = lm
        mRecyclerViewKRW.setHasFixedSize(true)

        return view
    }


}
