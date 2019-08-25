package study.architecture.myarchitecture.presenter

import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InOrder
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import study.architecture.myarchitecture.data.model.UpbitMarket
import study.architecture.myarchitecture.data.repository.UpbitRepository
import study.architecture.myarchitecture.ui.main.MainContract
import study.architecture.myarchitecture.ui.main.MainPresenter

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    @Mock
    private lateinit var view: MainContract.View

    @Mock
    private lateinit var repository: UpbitRepository

    private lateinit var presenter: MainPresenter

    private lateinit var inOrder: InOrder

    @Before
    fun setUp() {

        presenter = MainPresenter(repository, view)

        inOrder = Mockito.inOrder(repository, view)
    }

    @Test
    fun test_load_data_success() {

        val groupMarket: Map<String, List<UpbitMarket>> = mapOf(
            "KRW" to listOf(
                UpbitMarket(0, "KRW-BTC", "", ""),
                UpbitMarket(0, "KRW-DASH", "", "")
            ),
            "BTC" to listOf(
                UpbitMarket(0, "BTC-ETH", "", "")
            ),
            "ETH" to listOf(
                UpbitMarket(0, "ETH-LTC", "", "")
            ),
            "USDT" to listOf(
                UpbitMarket(0, "USDT-BTC", "", "")
            )
        )

        //given
        `when`(repository.getGroupedMarkets()).thenReturn(Single.just(groupMarket))

        //when
        presenter.loadData()

        //then
        verify(view).setViewPagerTitles(arrayOf("KRW", "BTC", "ETH", "USDT"))
        verify(view).setViewPagers(arrayOf("KRW-BTC,KRW-DASH", "BTC-ETH", "ETH-LTC", "USDT-BTC"))
    }

}
