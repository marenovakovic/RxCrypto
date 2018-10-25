package com.marko.remote.coins

import com.marko.domain.usecase.CoinId
import com.marko.remote.entities.CoinResponse
import com.marko.remote.entities.CoinsResponse
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinsService {

	@GET("listings")
	fun getCoins(): Flowable<CoinsResponse>

	@GET("ticker/{id}")
	fun getCoin(@Path("id") id: CoinId) : Single<CoinResponse>
}