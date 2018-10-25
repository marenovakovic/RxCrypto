package com.marko.remote.mappers

import com.marko.domain.entities.CoinEntity
import com.marko.remote.entities.CoinRemote

fun CoinEntity.toRemote(): CoinRemote = CoinRemote(
	id = id,
	name = name,
	symbol = symbol
)

fun List<CoinEntity>.toRemote(): List<CoinRemote> = map { it.toRemote() }

fun CoinRemote.toEntity(): CoinEntity = com.marko.domain.entities.CoinEntity(
	id = id,
	name = name,
	symbol = symbol
)

fun List<CoinRemote>.toEntity(): List<CoinEntity> = map { it.toEntity() }