package com.marko.domain.usecase.factory

import com.marko.domain.entities.CoinEntity
import com.marko.domain.usecase.CoinId
import java.util.*
import kotlin.math.roundToInt

object BaseFactory {

	val int = Math.random().roundToInt()
	val coinId: CoinId = int
	val string = UUID.randomUUID().toString()

	val coinEntity: CoinEntity = CoinEntity(
		id = int,
		name = string,
		symbol = string
	)

	val coinEntities: List<CoinEntity> = listOf(coinEntity, coinEntity, coinEntity)
}