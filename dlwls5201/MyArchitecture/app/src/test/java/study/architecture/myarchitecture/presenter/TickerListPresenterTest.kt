package study.architecture.myarchitecture.presenter

import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InOrder
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import study.architecture.myarchitecture.data.repository.UpbitRepository
import study.architecture.myarchitecture.ui.tickerlist.TickerListContract
import study.architecture.myarchitecture.ui.tickerlist.TickerListPresenter

@RunWith(MockitoJUnitRunner::class)
class TickerListPresenterTest {

    @Mock
    private lateinit var view: TickerListContract.View

    @Mock
    private lateinit var repository: UpbitRepository

    private lateinit var presenter: TickerListPresenter

    private lateinit var inOrder: InOrder

    @Before
    fun setUp() {

        presenter = TickerListPresenter(repository, view)

        inOrder = Mockito.inOrder(repository, view)
    }

    @Test
    fun test_load_data_success() {

        //given
        `when`(view.getKeyMarkets()).thenReturn("KRW-BTC,KRW-DASH")
        `when`(repository.getTickers("KRW-BTC,KRW-DASH"))
            .thenReturn(Single.just(listOf()))

        //when
        presenter.loadData()

        //then
        inOrder.verify(view).showProgress()
        inOrder.verify(view).hideProgress()
        inOrder.verify(view).setTickers(mutableListOf())

    }
}
