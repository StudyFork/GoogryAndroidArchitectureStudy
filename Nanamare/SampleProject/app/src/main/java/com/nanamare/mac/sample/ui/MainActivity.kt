package com.nanamare.mac.sample.ui

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.nanamare.mac.sample.R
import com.nanamare.mac.sample.api.DisposableManager
import com.nanamare.mac.sample.api.upbit.UpBitServiceManager
import io.reactivex.android.schedulers.AndroidSchedulers

class MainActivity : AppCompatActivity() {

    companion object {
        const val KET_MARKET_LIST = "key_market_list"
    }

    private lateinit var disposableManager: DisposableManager
    private lateinit var dialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dialog = ProgressDialog(this)
        dialog.setTitle("")
        dialog.setMessage(getString(R.string.loading))
        dialog.setCancelable(true)
        dialog.show()

        disposableManager = DisposableManager()
        disposableManager.add(
            UpBitServiceManager.getAllMarketList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    dialog.dismiss()
                    val marketMap: LinkedHashMap<String, List<String>> = LinkedHashMap()
                    val marketList = listOf<String>().toMutableList()
                    it.body()?.map { marketModel ->
                        marketModel.market!!.split("-")[0].let { market ->
                            marketList.add(marketModel.market)
                            marketMap.put(market, marketList)
                        }
                    }
                    val bundle = Bundle().apply {
                        putString(KET_MARKET_LIST, Gson().toJson(marketMap))
                    }
                    goToFragment(MarketListFragment::class.java, bundle)
                }, {
                    dialog.dismiss()
                })
        )
    }

    private fun goToFragment(cls: Class<*>, args: Bundle?) {
        try {
            val fragment = cls.newInstance() as Fragment
            fragment.arguments = args
            val fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction().replace(R.id.fl_content, fragment).commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        disposableManager.dispose()
        super.onDestroy()
    }


}