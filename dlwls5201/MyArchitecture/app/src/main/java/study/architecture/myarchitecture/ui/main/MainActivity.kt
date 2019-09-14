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

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel =
            MainViewModel(
                Injection.provideFolderRepository(this),
                MainAdapter(supportFragmentManager)
            ).apply {
                binding.mainModel = this
            }

        initToolbar()
        initDrawer()

        mainViewModel.loadData()

    }

    override fun onDestroy() {
        mainViewModel.detachView()
        super.onDestroy()
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
}
