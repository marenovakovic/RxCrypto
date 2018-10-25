package com.marko.rxcrypto.injection.activity

import com.marko.rxcrypto.home.MainModule
import com.marko.rxcrypto.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

	@ActivityScope
	@ContributesAndroidInjector(modules = [MainModule::class])
	abstract fun mainActivity(): MainActivity
}