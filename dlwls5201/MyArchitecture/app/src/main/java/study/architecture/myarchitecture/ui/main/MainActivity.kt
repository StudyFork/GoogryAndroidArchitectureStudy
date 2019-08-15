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
        initViewPager()

        presenter.loadData()

    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun showViewPagers(pagers: Array<String>) {
        setViewPagerPagerLimit(pagers.size)
        mainAdapter.setItems(pagers)
    }

    override fun showViewPagerTitles(titles: Array<String>) {
        mainAdapter.setTitles(titles)
    }

    override fun getArrowIsSelected(selectArrow: SelectArrow) =
        when (selectArrow) {
            SelectArrow.COIN_NAME -> ivSelectByCoinName.isSelected
            SelectArrow.LAST -> ivSelectByLast.isSelected
            SelectArrow.TRADE_DIFF -> ivSelectByTradeDiff.isSelected
            SelectArrow.TRADE_AMOUNT -> ivSelectByTradeAmount.isSelected
        }


    override fun setArrowSelected(selectArrow: SelectArrow, selected: Boolean) {
        when (selectArrow) {
            SelectArrow.COIN_NAME -> ivSelectByCoinName
            SelectArrow.LAST -> ivSelectByLast
            SelectArrow.TRADE_DIFF -> ivSelectByTradeDiff
            SelectArrow.TRADE_AMOUNT -> ivSelectByTradeAmount
        }.isSelected = selected
    }

    override fun setArrowVisibility(selectArrow: SelectArrow, visibility: Int) {
        when (selectArrow) {
            SelectArrow.COIN_NAME -> ivSelectByCoinName
            SelectArrow.LAST -> ivSelectByLast
            SelectArrow.TRADE_DIFF -> ivSelectByTradeDiff
            SelectArrow.TRADE_AMOUNT -> ivSelectByTradeAmount
        }.visibility = visibility
    }

    override fun showTickerListOrderByField(field: String, order: Int) {

        for (i: Int in 0 until mainAdapter.count) {
            mainAdapter.getFragment(i)?.run {
                this.get()?.showTickerListOrderByField(field, order)
            }
        }
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

    private fun initViewPager() {

        with(vpMain) {
            adapter = mainAdapter
            tlMain.setupWithViewPager(this)
        }

    }

    private fun setViewPagerPagerLimit(size: Int) {

        with(vpMain) {
            offscreenPageLimit = size
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
