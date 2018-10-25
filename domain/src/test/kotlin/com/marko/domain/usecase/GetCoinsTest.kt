package com.marko.domain.usecase

import com.marko.domain.TestFlowableTransformer
import com.marko.domain.coins.CoinsRepository
import com.marko.domain.entities.CoinEntity
import com.marko.domain.usecase.factory.BaseFactory
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import org.junit.Test

class GetCoinsTest {

	private val coinsRepository = mock<CoinsRepository>()
	private val getCoins = GetCoins(TestFlowableTransformer(), coinsRepository)

	@Test
	fun `does use case completes`() {
		stubFlowable(Flowable.just(BaseFactory.coinEntities))

		getCoins().test().assertComplete()
	}

	@Test
	fun `test use case return value`() {
		val coinEntity = BaseFactory.coinEntities

		stubFlowable(Flowable.just(coinEntity))

		getCoins().test().assertValue(coinEntity)
	}

	private fun stubFlowable(flowable: Flowable<List<CoinEntity>>) {
		whenever(coinsRepository.getCoins())
			.thenReturn(flowable)
	}
}