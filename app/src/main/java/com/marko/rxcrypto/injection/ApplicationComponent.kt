package com.marko.rxcrypto.injection

import android.app.Application
import com.marko.rxcrypto.app.App
import com.marko.rxcrypto.app.AppModule
import com.marko.rxcrypto.injection.activity.ActivityBindingModule
import com.marko.rxcrypto.injection.fragment.FragmentBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
	modules = [
		AndroidInjectionModule::class,
		AndroidSupportInjectionModule::class,
		AppModule::class,
		ActivityBindingModule::class,
		FragmentBindingModule::class
	]
)
interface ApplicationComponent {

	@Component.Builder
	interface Builder {
		@BindsInstance
		fun application(application: Application): Builder

		fun build(): ApplicationComponent
	}

	fun inject(app: App)
}

fun Application.createAppComponent() = DaggerApplicationComponent
	.builder()
	.application(this)
	.build()