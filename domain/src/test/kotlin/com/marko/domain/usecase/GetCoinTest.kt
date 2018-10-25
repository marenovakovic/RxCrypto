package com.marko.domain.usecase

import com.marko.domain.TestSingleTransformer
import com.marko.domain.coins.CoinsRepository
import com.marko.domain.entities.CoinEntity
import com.marko.domain.usecase.factory.BaseFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Test

class GetCoinTest {

	private val coinsRepository = mock<CoinsRepository>()
	private val getCoin = GetCoin(TestSingleTransformer(), coinsRepository)

	@Test
	fun `does use case completes`() {
		stubSingle(Single.just(BaseFactory.coinEntity))

		getCoin(BaseFactory.coinId).test().assertComplete()
	}

	@Test
	fun `check use case return value`() {
		val coinEntity = BaseFactory.coinEntity

		stubSingle(Single.just(coinEntity))

		getCoin(BaseFactory.coinId).test().assertValue(coinEntity)
	}

	private fun stubSingle(single: Single<CoinEntity>) {
		whenever(coinsRepository.getCoin(any()))
			.thenReturn(single)
	}
}