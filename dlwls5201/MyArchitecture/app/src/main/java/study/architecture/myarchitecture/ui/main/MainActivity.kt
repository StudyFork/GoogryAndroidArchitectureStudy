package study.architecture.myarchitecture.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.activity_main.*
import study.architecture.myarchitecture.BaseActivity
import study.architecture.myarchitecture.R
import study.architecture.myarchitecture.data.Injection
import study.architecture.myarchitecture.rxobserver.RxObserverHelper
import study.architecture.myarchitecture.util.Filter

class MainActivity : BaseActivity(), MainContract.View {

    enum class SelectArrow {
        COIN_NAME, LAST, TRADE_DIFF, TRADE_AMOUNT
    }

    private val mainAdapter by lazy { MainAdapter(supportFragmentManager) }

    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(
            Injection.provideFolderRepository(this),
            this@MainActivity
        )

        initToolbar()
        initDrawer()
        initTopCategory()

        presenter.loadData()

    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun setViewPagers(pagers: Array<String>) {
        initViewPager(pagers.size)
        mainAdapter.setItems(pagers)
    }

    override fun setViewPagerTitles(titles: Array<String>) {
        mainAdapter.setTitles(titles)
    }

    override fun getCoinNameIsSelected() = ivSelectByCoinName.isSelected

    override fun getLastIsSelected() = ivSelectByLast.isSelected

    override fun getTradeDiffIsSelected() = ivSelectByTradeDiff.isSelected

    override fun getTradeAmountIsSelected() = ivSelectByTradeAmount.isSelected

    override fun setCoinNameIsSelected(selected: Boolean) {
        ivSelectByCoinName.isSelected = selected
    }

    override fun setLastIsSelected(selected: Boolean) {
        ivSelectByLast.isSelected = selected
    }

    override fun setTradeDiffIsSelected(selected: Boolean) {
        ivSelectByTradeDiff.isSelected = selected
    }

    override fun setTradeAmountIsSelected(selected: Boolean) {
        ivSelectByTradeAmount.isSelected = selected
    }

    override fun setCoinNameVisibility(visibility: Int) {
        ivSelectByCoinName.visibility = visibility
    }

    override fun setLastVisibility(visibility: Int) {
        ivSelectByLast.visibility = visibility
    }

    override fun setTradeDiffVisibility(visibility: Int) {
        ivSelectByTradeDiff.visibility = visibility
    }

    override fun setTradeAmountVisibility(visibility: Int) {
        ivSelectByTradeAmount.visibility = visibility
    }

    override fun notifyTickerListObservers(field: String, order: Int) {

        val bundle = Bundle().apply {
            putString(Filter.KEY_FIELD, field)
            putInt(Filter.KEY_ORDER, order)
        }

        RxObserverHelper.notifyTickerList(bundle)
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
                    if (drawerView.id == R.id.flEndSideInDrawer) {
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

    private fun initViewPager(size: Int) {

        with(vpMain) {
            adapter = mainAdapter
            offscreenPageLimit = size
            tlMain.setupWithViewPager(this)
        }

    }

    private fun initTopCategory() {

        llCoinNameParent.setOnClickListener {
            presenter.changeArrow(SelectArrow.COIN_NAME)
        }

        llLastParent.setOnClickListener {
            presenter.changeArrow(SelectArrow.LAST)
        }

        llTradeDiffParent.setOnClickListener {
            presenter.changeArrow(SelectArrow.TRADE_DIFF)
        }

        llTradeAmountParent.setOnClickListener {
            presenter.changeArrow(SelectArrow.TRADE_AMOUNT)
        }
    }
}
