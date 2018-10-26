package com.marko.rxcrypto.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.marko.presentation.coins.CoinsViewModel
import com.marko.presentation.coins.CoinsViewModelFactory
import com.marko.rxcrypto.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

	@Inject
	lateinit var factory: CoinsViewModelFactory

	private val viewModel: CoinsViewModel by lazy {
		ViewModelProviders.of(this, factory).get(CoinsViewModel::class.java)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		// init ViewModel so fragments can use it
		viewModel
	}
}
