package com.marko.data.coins

import com.marko.domain.entities.CoinEntity
import com.marko.domain.usecase.CoinId
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface CoinsDataSource {

	fun getCoins(): Flowable<List<CoinEntity>>

	fun getCoin(id: CoinId): Single<CoinEntity>

	fun saveCoins(coins: List<CoinEntity>): Completable

	var isCached: Single<Boolean>

	var isCacheExpired: Boolean
}