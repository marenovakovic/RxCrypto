package com.marko.data.coins

import com.marko.domain.entities.CoinEntity
import com.marko.domain.usecase.CoinId
import io.reactivex.Flowable
import io.reactivex.Single

interface CoinsRemoteRepository {

	fun getCoins(): Flowable<List<CoinEntity>>

	fun getCoin(id: CoinId): Single<CoinEntity>
}