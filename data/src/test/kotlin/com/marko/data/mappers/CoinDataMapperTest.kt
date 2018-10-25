package com.marko.data.mappers

import com.marko.data.entities.CoinData
import com.marko.domain.entities.CoinEntity
import com.marko.domain.usecase.factory.BaseFactory
import org.junit.Test

class CoinDataMapperTest {

	@Test
	fun `test entity to data mapper`() {
		val coinEntity = BaseFactory.coinEntity
		val coinData = coinEntity.toData()

		assertCoins(coinEntity, coinData)
	}

	@Test
	fun `test list of entities to list of data mapper`() {
		val coinEntities = BaseFactory.coinEntities
		val coinDatas = coinEntities.toData()

		assertCoins(coinEntities, coinDatas)
	}

	private fun assertCoins(coinEntity: CoinEntity, coinData: CoinData) {
		assert(coinEntity.id == coinData.id)
		assert(coinEntity.name == coinData.name)
		assert(coinEntity.symbol == coinData.symbol)
	}
}