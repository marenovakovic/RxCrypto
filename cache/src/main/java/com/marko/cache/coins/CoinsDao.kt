package com.marko.cache.coins

import androidx.room.*
import com.marko.cache.entities.CoinCache
import com.marko.domain.usecase.CoinId
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface CoinsDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insertCoins(coins: List<CoinCache>)

	@Query("SELECT * FROM coins")
	fun getCoins(): Flowable<List<CoinCache>>

	@Query("SELECT * FROM coins WHERE id = :id")
	fun getCoin(id: CoinId): Single<CoinCache>

	@Delete
	fun deleteCoins(coins: List<CoinCache>)
}