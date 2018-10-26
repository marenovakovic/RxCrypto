package com.marko.cache.coins

import com.marko.cache.mappers.toCache
import com.marko.cache.mappers.toEntity
import com.marko.data.coins.CoinsCacheRepository
import com.marko.domain.entities.CoinEntity
import com.marko.domain.now
import com.marko.domain.usecase.CoinId
import com.marko.preferences.PreferenceStorage
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

private const val SECOND = 1000
private const val MINUTE = 60 * SECOND
private const val HOUR = 60 * MINUTE
private const val DAY = 24 * HOUR

private const val EXPIRATION_TIME = 10 * DAY

class CoinsCacheRepositoryImpl(
	private val preferences: PreferenceStorage,
	private val coinsDatabase: CoinsDatabase
) : CoinsCacheRepository {

	override fun getCoins(): Flowable<List<CoinEntity>> =
		coinsDatabase.coinsDao().getCoins().map { it.toEntity() }

	override fun getCoin(id: CoinId): Single<CoinEntity> =
		coinsDatabase.coinsDao().getCoin(id).map { it.toEntity() }

	override fun saveCoins(coins: List<CoinEntity>): Completable =
		Completable.fromAction {
			coinsDatabase.coinsDao().saveCoins(coins.toCache())
			preferences.lastCacheTime = now
		}

	override var isCached: Single<Boolean>
		get() = Single.defer { Single.just(coinsDatabase.coinsDao().getCount() > 0) }
		set(value) {}

	override var isCacheExpired: Boolean
		get() = now - preferences.lastCacheTime > EXPIRATION_TIME
		set(value) {}
}