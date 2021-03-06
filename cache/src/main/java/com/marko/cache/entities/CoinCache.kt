package com.marko.cache.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coins")
data class CoinCache(
	@PrimaryKey
	val id: Int,
	val name: String,
	val symbol: String
)