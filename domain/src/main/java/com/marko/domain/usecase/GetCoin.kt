package com.marko.domain.usecase

import com.marko.domain.SingleTransformer
import com.marko.domain.coins.CoinsRepository
import com.marko.domain.entities.CoinEntity
import io.reactivex.Single

typealias CoinId = Int

class GetCoin(
	transformer: SingleTransformer<CoinEntity>,
	private val coinsRepository: CoinsRepository
) : SingleUseCase<CoinId, CoinEntity>(transformer) {

	override fun createSingle(parameters: CoinId): Single<CoinEntity> =
		coinsRepository.getCoin(parameters)
}