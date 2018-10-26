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
	fun saveCoins(coins: List<CoinCache>)

	@Query("SELECT * FROM coins")
	fun getCoins(): Flowable<List<CoinCache>>

	@Query("SELECT * FROM coins WHERE id = :id")
	fun getCoin(id: CoinId): Single<CoinCache>

	@Query("SELECT count(*) FROM coins")
	fun getCount(): Int

	@Delete
	fun deleteCoins(coins: List<CoinCache>)
}