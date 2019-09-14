package study.architecture.myarchitecture.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import study.architecture.myarchitecture.R
import study.architecture.myarchitecture.base.BaseActivity
import study.architecture.myarchitecture.data.Injection
import study.architecture.myarchitecture.databinding.ActivityMainBinding
import study.architecture.myarchitecture.util.Filter

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main), MainContract.View {

    private val mainAdapter by lazy { MainAdapter(supportFragmentManager) }

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel =
            MainViewModel(Injection.provideFolderRepository(this)).apply {
                binding.mainModel = this
            }

        initToolbar()
        initDrawer()
        initTopCategory()
        initViewPager()

        mainViewModel.loadData()

    }

    override fun onDestroy() {
        mainViewModel.detachView()
        super.onDestroy()
    }

    override fun showViewPagers(pagers: Array<String>) {
        setViewPagerPagerLimit(pagers.size)
        mainAdapter.setItems(pagers)
    }

    private fun setViewPagerPagerLimit(size: Int) {

        with(binding.vpMain) {
            offscreenPageLimit = size
        }
    }

    override fun showViewPagerTitles(titles: Array<String>) {
        mainAdapter.setTitles(titles)
    }

    override fun showCategoryArrow(selectArrow: Filter.SelectArrow) {

        with(binding) {
            ivSelectByCoinName.visibility = View.INVISIBLE
            ivSelectByLast.visibility = View.INVISIBLE
            ivSelectByTradeDiff.visibility = View.INVISIBLE
            ivSelectByTradeAmount.visibility = View.INVISIBLE
        }

        val imageView = when (selectArrow) {
            Filter.SelectArrow.COIN_NAME -> binding.ivSelectByCoinName
            Filter.SelectArrow.LAST -> binding.ivSelectByLast
            Filter.SelectArrow.TRADE_DIFF -> binding.ivSelectByTradeDiff
            Filter.SelectArrow.TRADE_AMOUNT -> binding.ivSelectByTradeAmount
        }

        with(imageView) {
            visibility = View.VISIBLE
            if (isSelected) {
                showTickerListOrderByField(
                    selectArrow,
                    Filter.DESC
                )
            } else {
                showTickerListOrderByField(
                    selectArrow,
                    Filter.ASC
                )
            }

            isSelected = !isSelected
        }
    }

    private fun showTickerListOrderByField(field: Filter.SelectArrow, order: Int) {

        for (i: Int in 0 until mainAdapter.count) {
            mainAdapter.getFragment(i)?.run {
                this.get()?.showTickerListOrderByField(field, order)
            }
        }
    }

    private fun initToolbar() {

        with(binding) {
            llToolbarTitle.setOnClickListener {

                if (suplRoot.panelState == SlidingUpPanelLayout.PanelState.EXPANDED) {
                    suplRoot.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
                } else {
                    suplRoot.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
                }
            }

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
    }

    private fun initDrawer() {

        binding.dlRoot.run {
            setScrimColor(Color.TRANSPARENT)
            addDrawerListener(object : DrawerLayout.DrawerListener {
                override fun onDrawerStateChanged(newState: Int) {
                }

                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                    if (drawerView.id == R.id.flEndSideInDrawer) {
                        binding.llMainInDrawer.translationX =
                            -binding.llMainInDrawer.width * slideOffset
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

        with(binding.vpMain) {
            adapter = mainAdapter
            binding.tlMain.setupWithViewPager(this)
        }

    }

    private fun initTopCategory() {

        with(binding) {
            llCoinNameParent.setOnClickListener {
                showCategoryArrow(Filter.SelectArrow.COIN_NAME)
            }

            llLastParent.setOnClickListener {
                showCategoryArrow(Filter.SelectArrow.LAST)
            }

            llTradeDiffParent.setOnClickListener {
                showCategoryArrow(Filter.SelectArrow.TRADE_DIFF)
            }

            llTradeAmountParent.setOnClickListener {
                showCategoryArrow(Filter.SelectArrow.TRADE_AMOUNT)
            }
        }
    }
}
