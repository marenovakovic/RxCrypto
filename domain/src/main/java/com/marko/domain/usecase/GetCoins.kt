package com.marko.domain.usecase

import com.marko.domain.FlowableTransformer
import com.marko.domain.coins.CoinsRepository
import com.marko.domain.entities.CoinEntity
import io.reactivex.Flowable

class GetCoins(
	transformer: FlowableTransformer<List<CoinEntity>>,
	private val coinsRepository: CoinsRepository
) : FlowableUseCase<Unit, List<CoinEntity>>(transformer) {

	override fun createFlowable(parameters: Unit): Flowable<List<CoinEntity>> =
		coinsRepository.getCoins()
}