package com.marko.data.mappers.factory

import com.marko.data.entities.CoinData
import com.marko.domain.usecase.factory.BaseFactory

object DataTestFactory {

	val coinData: CoinData = CoinData(
		id = BaseFactory.int,
		name = BaseFactory.string,
		symbol = BaseFactory.string
	)

	val coinDatas: List<CoinData> = listOf(coinData, coinData, coinData)
}