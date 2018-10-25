package com.marko.remote.retrofit

import com.marko.remote.coins.CoinsService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val WRITE_TIMEOUT = 15L
private const val READ_TIMEOUT = 15L

private const val BASE_URL = "https://api.coinmarketcap.com/v2/"

object CoinsApi {

	private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

	private val okHttpClient = OkHttpClient.Builder()
		.addInterceptor(logger)
		.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
		.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
		.build()

	private val retrofit = Retrofit.Builder()
		.baseUrl(BASE_URL)
		.client(okHttpClient)
		.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
		.addConverterFactory(GsonConverterFactory.create())
		.build()

	val coinsService: CoinsService = retrofit.create(CoinsService::class.java)
}