package com.marko.domain.usecase

import com.marko.domain.SingleTransformer
import io.reactivex.Single

abstract class SingleUseCase<in P, R>(private val transformer: SingleTransformer<R>) {

	abstract fun createSingle(parameters: P): Single<R>

	private fun execute(parameters: P): Single<R> =
		createSingle(parameters).compose(transformer)

	operator fun invoke(parameters: P): Single<R> = execute(parameters)
}

operator fun <R> SingleUseCase<Unit, R>.invoke(): Single<R> = this(Unit)