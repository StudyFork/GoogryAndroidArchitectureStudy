package kr.schoolsharing.coinhelper.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.schoolsharing.coinhelper.R
import kr.schoolsharing.coinhelper.data.UpbitItem


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentBTC(val itemList: List<UpbitItem>) : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fragment_btc, container, false)
        // Inflate the layout for this fragment
        val mRecyclerViewKRW = view.findViewById<RecyclerView>(R.id.mRecyclerView_BTC)


        val myRVAdapter = MainRvAdapter(context!!, itemList)
        mRecyclerViewKRW.adapter = myRVAdapter

        val lm = LinearLayoutManager(context!!)
        mRecyclerViewKRW.layoutManager = lm
        mRecyclerViewKRW.setHasFixedSize(true)

        return view
    }
}
