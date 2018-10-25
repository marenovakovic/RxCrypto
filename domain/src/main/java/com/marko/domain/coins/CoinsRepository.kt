package com.marko.domain.coins

import com.marko.domain.entities.CoinEntity
import com.marko.domain.usecase.CoinId
import io.reactivex.Flowable
import io.reactivex.Single

interface CoinsRepository {

	fun getCoins(): Flowable<List<CoinEntity>>

	fun getCoin(id: CoinId): Single<CoinEntity>
}