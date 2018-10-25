package com.marko.rxcrypto.transformer

import com.marko.domain.SingleTransformer
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AsyncSingleTransformer<T> : SingleTransformer<T>() {

	override fun apply(upstream: Single<T>): SingleSource<T> =
		upstream.subscribeOn(Schedulers.io())
		.observeOn(AndroidSchedulers.mainThread())

}