package io.github.sooakim.util.ext

import androidx.databinding.ObservableField
import io.reactivex.Observable
import io.reactivex.ObservableEmitter

val <E> ObservableField<E>.rx: Observable<E>
    get() {
        return Observable.create<E> { emitter: ObservableEmitter<E> ->
            if (!emitter.isDisposed && get() != null) {
                emitter.onNext(get()!!)
            }

            val callback = object : androidx.databinding.Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(
                    sender: androidx.databinding.Observable?,
                    propertyId: Int
                ) {
                    if (!emitter.isDisposed && get() != null) {
                        emitter.onNext(get()!!)
                    }
                }
            }
            this.addOnPropertyChangedCallback(callback)

            emitter.setCancellable {
                this.removeOnPropertyChangedCallback(callback)
            }
        }
    }