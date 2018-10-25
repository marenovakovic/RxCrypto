package com.marko.rxcrypto.injection.fragment

import com.marko.rxcrypto.coins.CoinsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

	@FragmentScope
	@ContributesAndroidInjector
	abstract fun coinsListFragment(): CoinsListFragment
}