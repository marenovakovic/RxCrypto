package com.marko.data.coins

import com.marko.domain.entities.CoinEntity
import com.marko.domain.usecase.CoinId
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class CoinsCacheDataSource(
	private val coinsCacheRepository: CoinsCacheRepository
) : CoinsDataSource {

	override fun getCoins(): Flowable<List<CoinEntity>> = coinsCacheRepository.getCoins()

	override fun getCoin(id: CoinId): Single<CoinEntity> = coinsCacheRepository.getCoin(id)

	override fun saveCoins(coins: List<CoinEntity>): Completable =
		coinsCacheRepository.saveCoins(coins)

	override var isCached: Single<Boolean>
		get() = coinsCacheRepository.isCached
		set(value) {}

	override var isCacheExpired: Boolean
		get() = coinsCacheRepository.isCacheExpired
		set(value) {}
}