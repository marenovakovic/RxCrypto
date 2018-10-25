package com.marko.cache.mappers

import com.marko.cache.entities.CoinCache
import com.marko.domain.entities.CoinEntity

fun CoinEntity.toCache(): CoinCache = CoinCache(
	id = id,
	name = name,
	symbol = symbol
)

fun List<CoinEntity>.toCache(): List<CoinCache> = map { it.toCache() }

fun CoinCache.toEntity(): CoinEntity = CoinEntity(
	id = id,
	name = name,
	symbol = symbol
)

fun List<CoinCache>.toEntity(): List<CoinEntity> = map { it.toEntity() }