package com.marko.cache.coins

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marko.cache.entities.CoinCache

@Database(entities = [CoinCache::class], version = 1, exportSchema = false)
abstract class CoinsDatabase : RoomDatabase() {

	abstract fun coinsDao(): CoinsDao

	companion object {
		private var instance: CoinsDatabase? = null

		fun getInstance(context: Context): CoinsDatabase {
			return instance ?: synchronized(Any()) {
				instance = Room.databaseBuilder(
					context.applicationContext,
					CoinsDatabase::class.java,
					"coins.db"
				)
					.build()
				instance !!
			}
		}
	}
}