package com.marko.domain

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.SingleSource
import org.reactivestreams.Publisher

class TestFlowableTransformer<T> : FlowableTransformer<T>() {

	override fun apply(upstream: Flowable<T>): Publisher<T> = upstream
}

class TestSingleTransformer<T> : SingleTransformer<T>() {

	override fun apply(upstream: Single<T>): SingleSource<T> = upstream
}