package com.marko.remote.coins

import com.marko.data.coins.CoinsRemoteRepository
import com.marko.domain.entities.CoinEntity
import com.marko.domain.usecase.CoinId
import com.marko.remote.mappers.toEntity
import com.marko.remote.retrofit.CoinsApi
import io.reactivex.Flowable
import io.reactivex.Single

class CoinsRemoteRepositoryImpl : CoinsRemoteRepository {

	override fun getCoins(): Flowable<List<CoinEntity>> =
		CoinsApi.coinsService.getCoins().map { it.data.map { it.toEntity() } }

	override fun getCoin(id: CoinId): Single<CoinEntity> =
		CoinsApi.coinsService.getCoin(id).map { it.data.toEntity() }
}