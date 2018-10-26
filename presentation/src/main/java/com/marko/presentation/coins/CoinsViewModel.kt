package com.marko.presentation.coins

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marko.domain.usecase.CoinId
import com.marko.domain.usecase.GetCoin
import com.marko.domain.usecase.GetCoins
import com.marko.presentation.base.BaseViewModel
import com.marko.presentation.base.Event
import com.marko.presentation.entities.Coin
import com.marko.presentation.mappers.toPresentation
import timber.log.Timber

class CoinsViewModel(
	private val getCoins: GetCoins,
	private val getCoin: GetCoin
) : BaseViewModel() {

	private val _loading = MutableLiveData<Event<Boolean>>()
	val loading: LiveData<Event<Boolean>>
		get() = _loading

	private val _coins = MutableLiveData<List<Coin>>()
	val coins: LiveData<List<Coin>>
		get() = _coins

	private val _coin = MutableLiveData<Coin>()
	val coin: LiveData<Coin>
		get() = _coin

	private val _error = MutableLiveData<Event<Throwable>>()
	val error: LiveData<Event<Throwable>>
		get() = _error

	fun fetch() {
		_loading.value = Event(true)
		addDisposable {
			getCoins(Unit)
				.subscribe(
					{
						_coins.value = it.toPresentation()
						_loading.value = Event(false)
					},
					{
						Timber.e(it)
						_error.value = Event(it)
						_loading.value = Event(false)
					}
				)
		}
	}

	fun fetchCoin(id: CoinId) {
		_loading.value = Event(true)
		addDisposable {
			getCoin(id)
				.subscribe(
					{
						_coin.value = it.toPresentation()
						_loading.value = Event(false)
					},
					{
						_error.value = Event(it)
						_loading.value = Event(false)
					}
				)
		}
	}
}