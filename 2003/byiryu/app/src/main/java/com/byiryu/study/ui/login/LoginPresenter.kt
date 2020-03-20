package com.byiryu.study.ui.login

import com.byiryu.study.R
import com.byiryu.study.model.Repository
import com.byiryu.study.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers

class LoginPresenter<V : LoginContract.View> constructor(private val repository: Repository) :
    BasePresenter<V>(), LoginContract.Presenter<V> {

    override fun login(id: String, pw: String, autoLogin: Boolean) {

        if (id.isEmpty()) {
            mvpView?.showMsg(R.string.msg_invalid_id)
            return
        }

        if (pw.isEmpty()) {
            mvpView?.showMsg(R.string.msg_invalid_pw)
            return
        }

        disposable?.add( repository.loginCheck(id, pw)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                mvpView?.showLoading()
            }.doOnError {
                mvpView?.showMsg(R.string.msg_error_loading)
            }.doAfterTerminate {
                mvpView?.hideLoading()
            }
            .subscribe({
                if (it) {
                    if (autoLogin) {
                        repository.setAutoLogin()
                    }
                    mvpView?.goActivityMain()
                } else {
                    mvpView?.showMsg(R.string.msg_incorrect_login)
                }
            }, {
                mvpView?.showMsg("오류 발생 : $it")
            })
        )
    }

}
