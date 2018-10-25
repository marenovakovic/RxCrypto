package com.marko.data.coins

import com.marko.domain.coins.CoinsRepository
import com.marko.domain.entities.CoinEntity
import com.marko.domain.usecase.CoinId
import io.reactivex.Flowable
import io.reactivex.Single

class CoinsRepositoryImpl(
	dataSourceFactory: DataSourceFactory
) : CoinsRepository {

	private val dataSource = dataSourceFactory.dataSource

	override fun getCoins(): Flowable<List<CoinEntity>> = dataSource.getCoins()

	override fun getCoin(id: CoinId): Single<CoinEntity> = dataSource.getCoin(id)
}