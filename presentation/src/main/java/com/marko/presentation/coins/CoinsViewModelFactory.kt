package com.marko.presentation.coins

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marko.domain.usecase.GetCoin
import com.marko.domain.usecase.GetCoins

class CoinsViewModelFactory(
	private val getCoins: GetCoins,
	private val getCoin: GetCoin
) : ViewModelProvider.Factory {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T =
		CoinsViewModel(getCoins, getCoin) as T
}