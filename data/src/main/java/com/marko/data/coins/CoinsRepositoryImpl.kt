package com.marko.data.coins

import com.marko.domain.coins.CoinsRepository
import com.marko.domain.entities.CoinEntity
import com.marko.domain.usecase.CoinId
import io.reactivex.Flowable
import io.reactivex.Single

class CoinsRepositoryImpl(
	private val dataSourceFactory: DataSourceFactory
) : CoinsRepository {

	private val dataSource = dataSourceFactory.dataSource

	override fun getCoins(): Flowable<List<CoinEntity>> =
		dataSourceFactory.cacheDataSource.isCached
			.flatMapPublisher {
				if (it) {
					dataSource.getCoins()
				} else {
					dataSource.getCoins()
						.flatMap {
							dataSourceFactory.cacheDataSource.saveCoins(it)
								.toSingle { it }.toFlowable()
						}
				}
			}

	override fun getCoin(id: CoinId): Single<CoinEntity> = dataSource.getCoin(id)
}