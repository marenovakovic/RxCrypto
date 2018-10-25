package com.marko.domain.usecase

import com.marko.domain.FlowableTransformer
import io.reactivex.Flowable

abstract class FlowableUseCase<in P, R>(private val transformer: FlowableTransformer<R>) {

	abstract fun createFlowable(parameters: P): Flowable<R>

	private fun execute(parameters: P): Flowable<R> =
		createFlowable(parameters).compose(transformer)

	operator fun invoke(parameters: P): Flowable<R> = execute(parameters)
}

operator fun <R> FlowableUseCase<Unit, R>.invoke(): Flowable<R> = this(Unit)