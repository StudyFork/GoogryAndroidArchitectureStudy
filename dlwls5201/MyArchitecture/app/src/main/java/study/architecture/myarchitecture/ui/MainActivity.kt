package study.architecture.myarchitecture.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.drawerlayout.widget.DrawerLayout
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import study.architecture.myarchitecture.BaseActivity
import study.architecture.myarchitecture.RxEventBus.RxEventBusHelper
import study.architecture.myarchitecture.data.ApiProvider
import study.architecture.myarchitecture.repository.UpbitRepository
import study.architecture.myarchitecture.repository.UpbitRepositoryImpl
import timber.log.Timber

class MainActivity : BaseActivity() {

    enum class SelectArrow {
        COIN_NAME, LAST, TRADE_DIFF, TRADE_AMOUNT
    }

    private val mainAdapter by lazy { MainAdapter(supportFragmentManager) }

    private lateinit var upbitRepository: UpbitRepository

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(study.architecture.myarchitecture.R.layout.activity_main)

        upbitRepository = UpbitRepositoryImpl(
            ApiProvider.provideUpbitApi()
        )

        initToolbar()
        initDrawer()
        initViewPager()
        initTopCategory()
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

    private fun initTopCategory() {

        llCoinNameParent.setOnClickListener {
            changeArrow(SelectArrow.COIN_NAME)
        }

        llLastParent.setOnClickListener {
            changeArrow(SelectArrow.LAST)
        }

        llTradeDiffParent.setOnClickListener {
            changeArrow(SelectArrow.TRADE_DIFF)
        }

        llTradeAmountParent.setOnClickListener {
            changeArrow(SelectArrow.TRADE_AMOUNT)
        }
    }

    private fun loadData() {

        upbitRepository.getGroupedMarkets()
            .subscribe({ groupMarket ->

                val keys = groupMarket.keys

                mainAdapter.setTitles(keys.toTypedArray())

                val arrMarkets = Array(keys.size) { "" }

                for ((index, value) in keys.withIndex()) {

                    arrMarkets[index] = groupMarket
                        .getValue(value)
                        .joinToString(separator = ",") { it.market }
                }

                mainAdapter.setItems(arrMarkets)

            }) {
                Timber.e(it.message)
            }.also {
                compositeDisposable.add(it)
            }
    }

    private fun changeArrow(selectArrow: SelectArrow) {

        ivSelectByCoinName.visibility = View.INVISIBLE
        ivSelectByLast.visibility = View.INVISIBLE
        ivSelectByTradeDiff.visibility = View.INVISIBLE
        ivSelectByTradeAmount.visibility = View.INVISIBLE

        val bundle = Bundle()

        when (selectArrow) {

            SelectArrow.COIN_NAME -> {
                setArrowImage(ivSelectByCoinName, bundle, TickerAdapter.COIN_NAME)
            }

            SelectArrow.LAST -> {
                setArrowImage(ivSelectByLast, bundle, TickerAdapter.LAST)
            }

            SelectArrow.TRADE_DIFF -> {
                setArrowImage(ivSelectByTradeDiff, bundle, TickerAdapter.TRADE_DIFF)
            }

            SelectArrow.TRADE_AMOUNT -> {
                setArrowImage(ivSelectByTradeAmount, bundle, TickerAdapter.TRADE_AMOUNT)
            }
        }

        RxEventBusHelper.sendEvent(bundle)
    }

    private fun setArrowImage(ivArrow: ImageView, bundle: Bundle, field: String) {

        ivArrow.visibility = View.VISIBLE

        bundle.putString(TickerAdapter.KEY_FIELD, field)

        if (ivArrow.isSelected) {
            //내림차순
            bundle.putInt(TickerAdapter.KEY_ORDER, TickerAdapter.DESC)
        } else {
            //오름차순
            bundle.putInt(TickerAdapter.KEY_ORDER, TickerAdapter.ASC)
        }

        ivArrow.isSelected = !ivArrow.isSelected
    }
}
