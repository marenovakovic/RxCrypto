package com.marko.rxcrypto.transformer

import com.marko.domain.FlowableTransformer
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Publisher

class AsyncFlowableTransformer<T> : FlowableTransformer<T>() {

	// ne mora da se radi observeOn(AndroidSchedulers.mainThread()) cak je u ovom slucaju
	// i pozeljno zato sto nema overhead prebacivanja sa thread-a na thread
	// i pozivamo liveData.postValue umesto liveData.setValue
	override fun apply(upstream: Flowable<T>): Publisher<T> =
		upstream
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
}