package kr.schoolsharing.coinhelper


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class FragmentKRW : Fragment() {

    var itemList = arrayListOf<UpbitItem>(
        UpbitItem("XRP", "7,744,543", "-1.99%", "48,559 M"),
        UpbitItem("TRX", "543", "-1.92%", "48,559 M"),
        UpbitItem("BTC", "543", "3.99%", "9 M"),
        UpbitItem("MEDX", "543", "-0.99%", "59 M"),
        UpbitItem("ETH", "543", "-0.19%", "4,859 M"),
        UpbitItem("EOS", "543", "0.99%", "559 M")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_fragment_krw, container, false)
        // Inflate the layout for this fragment
        val mRecyclerViewKRW = view.findViewById<RecyclerView>(R.id.mRecyclerView_KRW)


        val myRVAdapter = MainRvAdapter(context!!, itemList)
        mRecyclerViewKRW.adapter = myRVAdapter

        val lm = LinearLayoutManager(context!!)
        mRecyclerViewKRW.layoutManager = lm
        mRecyclerViewKRW.setHasFixedSize(true)

        return view
    }
}
