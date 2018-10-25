package com.marko.data.coins

class DataSourceFactory(
	private val coinsRemoteDataSource: CoinsRemoteDataSource,
	private val coinsCacheDataSource: CoinsCacheDataSource
) {
	val dataSource: CoinsDataSource
		get() =
			if (! coinsCacheDataSource.isCacheExpired) coinsCacheDataSource
			else coinsRemoteDataSource
}