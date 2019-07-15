package study.architecture.myarchitecture.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.RotateAnimation
import androidx.drawerlayout.widget.DrawerLayout
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import study.architecture.myarchitecture.BaseActivity
import study.architecture.myarchitecture.network.ApiProvider
import study.architecture.myarchitecture.util.Dlog
import java.util.regex.Pattern

class MainActivity : BaseActivity() {

    private val mainAdapter by lazy { MainAdapter(supportFragmentManager) }

    private val upbitApi = ApiProvider.provideUpbitApi()

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(study.architecture.myarchitecture.R.layout.activity_main)

        initToolbar()
        initDrawer()
        initViewPager()
        loadData()

    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    private fun initToolbar() {

        llToolbarTitle.setOnClickListener {

            if (suplRoot.panelState == SlidingUpPanelLayout.PanelState.EXPANDED) {
                suplRoot.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
            } else {
                suplRoot.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
            }
        }

        suplRoot.addPanelSlideListener(object : SlidingUpPanelLayout.PanelSlideListener {
            override fun onPanelSlide(panel: View?, slideOffset: Float) {
                ivToolbarArrow.rotation = slideOffset * 180
            }

            override fun onPanelStateChanged(
                panel: View?,
                previousState: SlidingUpPanelLayout.PanelState?,
                newState: SlidingUpPanelLayout.PanelState?
            ) {

            }

        })

        ivToolbarMenu.setOnClickListener {
            dlRoot.openDrawer(flEndSideInDrawer)
        }
    }

    private fun initDrawer() {

        dlRoot.run {
            setScrimColor(Color.TRANSPARENT)
            addDrawerListener(object : DrawerLayout.DrawerListener {
                override fun onDrawerStateChanged(newState: Int) {
                }

                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                    if (drawerView.id == study.architecture.myarchitecture.R.id.flEndSideInDrawer) {
                        llMainInDrawer.translationX = -llMainInDrawer.width * slideOffset
                    }
                }

                override fun onDrawerClosed(drawerView: View) {
                }

                override fun onDrawerOpened(drawerView: View) {
                }
            })
        }
    }

    private fun initViewPager() {

        with(vpMain) {
            adapter = mainAdapter
            tlMain.setupWithViewPager(this)
        }


    }

    private fun loadData() {

        upbitApi.getMarkets()
            .flatMap { markets ->

                val pattern = Pattern.compile("^([a-zA-Z]*)-([a-zA-Z]*)$")

                val groupMarket = markets
                    .filter { pattern.matcher(it.market).find() }
                    .groupBy {
                        val idx = it.market.indexOf("-")
                        it.market.substring(0, idx)
                    }

                val keys = groupMarket.keys

                mainAdapter.setTitles(keys.toTypedArray())

                val arrMarkets = Array(keys.size) { "" }

                for ((index, value) in keys.withIndex()) {
                    Dlog.d("index : $index -> value : $value")

                    arrMarkets[index] = groupMarket
                        .getValue(value)
                        .joinToString(separator = ",") { it.market }
                }

                Single.just(arrMarkets)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ items ->
                mainAdapter.setItems(items)
            }) {
                Dlog.e(it.message)
            }.also {
                compositeDisposable.add(it)
            }
    }
}
