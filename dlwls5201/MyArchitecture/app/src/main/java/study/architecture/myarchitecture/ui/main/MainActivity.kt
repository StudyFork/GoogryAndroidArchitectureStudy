package study.architecture.myarchitecture.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.drawerlayout.widget.DrawerLayout
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.activity_main.*
import study.architecture.myarchitecture.BaseActivity
import study.architecture.myarchitecture.R
import study.architecture.myarchitecture.data.Injection
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
        initViewPager()
        initTopCategory()

    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun setViewPagers(pagers: Array<String>) {
        mainAdapter.setItems(pagers)
    }

    override fun setViewPagerTitles(titles: Array<String>) {
        mainAdapter.setTitles(titles)
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

    private fun changeArrow(selectArrow: SelectArrow) {

        ivSelectByCoinName.visibility = View.INVISIBLE
        ivSelectByLast.visibility = View.INVISIBLE
        ivSelectByTradeDiff.visibility = View.INVISIBLE
        ivSelectByTradeAmount.visibility = View.INVISIBLE

        val bundle = Bundle()

        when (selectArrow) {

            SelectArrow.COIN_NAME -> {
                setArrowImage(ivSelectByCoinName, bundle, Filter.COIN_NAME)
            }

            SelectArrow.LAST -> {
                setArrowImage(ivSelectByLast, bundle, Filter.LAST)
            }

            SelectArrow.TRADE_DIFF -> {
                setArrowImage(ivSelectByTradeDiff, bundle, Filter.TRADE_DIFF)
            }

            SelectArrow.TRADE_AMOUNT -> {
                setArrowImage(ivSelectByTradeAmount, bundle, Filter.TRADE_AMOUNT)
            }
        }

        presenter.sendEventBus(bundle)
    }

    private fun setArrowImage(ivArrow: ImageView, bundle: Bundle, field: String) {

        ivArrow.visibility = View.VISIBLE

        bundle.putString(Filter.KEY_FIELD, field)

        if (ivArrow.isSelected) {
            //내림차순
            bundle.putInt(Filter.KEY_ORDER, Filter.DESC)
        } else {
            //오름차순
            bundle.putInt(Filter.KEY_ORDER, Filter.ASC)
        }

        ivArrow.isSelected = !ivArrow.isSelected
    }
}
