package com.marko.rxcrypto.app

import android.app.Activity
import android.app.Application
import com.marko.rxcrypto.BuildConfig
import com.marko.rxcrypto.injection.ApplicationComponent
import com.marko.rxcrypto.injection.createAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

lateinit var appComponent: ApplicationComponent

class App : Application(), HasActivityInjector {

	@Inject
	lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

	override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

	override fun onCreate() {
		super.onCreate()

		if (BuildConfig.DEBUG) {
			Timber.plant(Timber.DebugTree())
		}

		appComponent = createAppComponent()

		appComponent.inject(this)
	}
}