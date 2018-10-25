package com.marko.rxcrypto.home

import android.content.Context
import com.marko.cache.coins.CoinsCacheRepositoryImpl
import com.marko.cache.coins.CoinsDatabase
import com.marko.data.coins.*
import com.marko.domain.coins.CoinsRepository
import com.marko.domain.usecase.GetCoin
import com.marko.domain.usecase.GetCoins
import com.marko.preferences.PreferenceStorage
import com.marko.presentation.coins.CoinsViewModelFactory
import com.marko.remote.coins.CoinsRemoteRepositoryImpl
import com.marko.rxcrypto.transformer.AsyncFlowableTransformer
import com.marko.rxcrypto.transformer.AsyncSingleTransformer
import dagger.Module
import dagger.Provides

@Module
class MainModule {

	@Provides
	fun provideCoinsDatabase(context: Context): CoinsDatabase =
		CoinsDatabase.getInstance(context)

	@Provides
	fun provideCoinsCacheRepository(
		preferences: PreferenceStorage,
		coinsDatabase: CoinsDatabase
	): CoinsCacheRepository = CoinsCacheRepositoryImpl(preferences, coinsDatabase)

	@Provides
	fun provideCoinsCacheDataSource(coinsCacheRepository: CoinsCacheRepository): CoinsCacheDataSource =
		CoinsCacheDataSource(coinsCacheRepository)

	@Provides
	fun provideCoinsRemoteRepository(): CoinsRemoteRepository =
		CoinsRemoteRepositoryImpl()

	@Provides
	fun provideCoinsRemoteDataSource(coinsRemoteRepository: CoinsRemoteRepository): CoinsRemoteDataSource =
		CoinsRemoteDataSource(coinsRemoteRepository)

	@Provides
	fun provideDataSourceFactory(
		coinsRemoteDataSource: CoinsRemoteDataSource,
		coinsCacheDataSource: CoinsCacheDataSource
	): DataSourceFactory = DataSourceFactory(coinsRemoteDataSource, coinsCacheDataSource)

	@Provides
	fun provideCoinsRepository(dataSourceFactory: DataSourceFactory): CoinsRepository =
		CoinsRepositoryImpl(dataSourceFactory)

	@Provides
	fun provideGetCoinUseCase(coinsRepository: CoinsRepository): GetCoin =
		GetCoin(AsyncSingleTransformer(), coinsRepository)

	@Provides
	fun provideGetCoinsUseCase(coinsRepository: CoinsRepository): GetCoins =
		GetCoins(AsyncFlowableTransformer(), coinsRepository)

	@Provides
	fun provideCoinsViewModelFactory(
		getCoins: GetCoins,
		getCoin: GetCoin
	): CoinsViewModelFactory = CoinsViewModelFactory(getCoins, getCoin)
}