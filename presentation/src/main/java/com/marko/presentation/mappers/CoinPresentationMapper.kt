package com.marko.presentation.mappers

import com.marko.domain.entities.CoinEntity
import com.marko.presentation.entities.Coin

fun CoinEntity.toPresentation(): Coin = Coin(
	id = id,
	name = name,
	symbol = symbol
)

fun List<CoinEntity>.toPresentation(): List<Coin> = map { it.toPresentation() }

fun Coin.toEntity(): CoinEntity = com.marko.domain.entities.CoinEntity(
	id = id,
	name = name,
	symbol = symbol
)

fun List<Coin>.toEntity(): List<CoinEntity> = map { it.toEntity() }