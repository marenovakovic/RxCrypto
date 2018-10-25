package com.marko.data.mappers

import com.marko.data.entities.CoinData
import com.marko.domain.entities.CoinEntity

fun CoinEntity.toData(): CoinData = CoinData(
	id = id,
	name = name,
	symbol = symbol
)

fun List<CoinEntity>.toData(): List<CoinData> = map { it.toData() }

fun CoinData.toEntity(): CoinEntity = CoinEntity(
	id = id,
	name = name,
	symbol = symbol
)

fun List<CoinData>.toEntity(): List<CoinEntity> = map { it.toEntity() }