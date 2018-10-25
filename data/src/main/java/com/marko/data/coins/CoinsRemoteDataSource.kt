package com.marko.data.coins

import com.marko.domain.entities.CoinEntity
import com.marko.domain.usecase.CoinId
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class CoinsRemoteDataSource(
	private val coinsRemoteRepository: CoinsRemoteRepository
) : CoinsDataSource {

	override fun getCoins(): Flowable<List<CoinEntity>> = coinsRemoteRepository.getCoins()

	override fun getCoin(id: CoinId): Single<CoinEntity> = coinsRemoteRepository.getCoin(id)

	override fun saveCoins(coins: List<CoinEntity>): Completable =
		throw UnsupportedOperationException("CoinsRemoteDataSource doesn't cached coins")

	override var isCached: Single<Boolean>
		get() = throw UnsupportedOperationException("CoinsRemoteDataSource doesn't cached coins")
		set(value) {}

	override var isCacheExpired: Boolean
		get() = throw UnsupportedOperationException("CoinsRemoteDataSource doesn't cached coins")
		set(value) {}

}