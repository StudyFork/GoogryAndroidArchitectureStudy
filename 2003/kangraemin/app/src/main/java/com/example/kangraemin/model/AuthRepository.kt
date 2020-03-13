package com.example.kangraemin.model

import android.content.Context
import com.example.kangraemin.model.local.datadao.AuthImpl
import com.example.kangraemin.model.local.datamodel.Auth
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class AuthRepository {

    fun getAuth(context: Context): Flowable<Auth> {
        return Flowable
            .create({ emitter ->
                val db = AppDatabase.getInstance(context = context)
                AuthImpl(db = db)
                    .getAuth()
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        emitter.onNext(it)
                    }, { emitter.onError(it) })
            }, BackpressureStrategy.BUFFER)
    }

    fun deleteAuth(context: Context): Completable {
        return Completable
            .create { emitter ->
                val db = AppDatabase.getInstance(context = context)
                AuthImpl(db = db)
                    .deleteAuth()
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        emitter.onComplete()
                    }, { emitter.onError(it) })
            }
    }

    fun addAuth(context: Context, auth: Auth): Completable {
        return Completable
            .create { emitter ->
                val db = AppDatabase.getInstance(context = context)
                AuthImpl(db = db)
                    .addAuth(auth = auth)
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        emitter.onComplete()
                    }, { emitter.onError(it) })
            }
    }

    companion object {
        private var authRepo: AuthRepository? = null

        @JvmStatic
        fun getAuthRepo(): AuthRepository {
            if (authRepo == null) {
                authRepo = AuthRepository()
            }
            return authRepo!!
        }
    }
}