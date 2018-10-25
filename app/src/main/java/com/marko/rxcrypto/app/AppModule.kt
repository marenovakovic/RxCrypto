package com.marko.rxcrypto.app

import android.app.Application
import android.content.Context
import com.marko.preferences.PreferenceStorage
import com.marko.preferences.SharedPreferenceStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

	@Provides
	fun provideContext(application: Application): Context = application

	@Singleton
	@Provides
	fun providePreferences(context: Context): PreferenceStorage =
		SharedPreferenceStorage(context)
}