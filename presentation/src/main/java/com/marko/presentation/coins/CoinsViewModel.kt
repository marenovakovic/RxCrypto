package com.marko.presentation.coins

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marko.domain.entities.CoinEntity
import com.marko.domain.usecase.CoinId
import com.marko.domain.usecase.GetCoin
import com.marko.domain.usecase.GetCoins
import com.marko.presentation.base.BaseViewModel
import com.marko.presentation.base.Event
import com.marko.presentation.entities.Coin
import com.marko.presentation.mappers.toPresentation
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.subscribers.DisposableSubscriber
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
		addDisposable {
			getCoins(Unit)
				.subscribeWith(object : DisposableSubscriber<List<CoinEntity>>() {
					override fun onStart() {
						super.onStart()
						_loading.value = Event(true)
					}

					override fun onNext(coins: List<CoinEntity>) {
						_coins.value = coins.toPresentation()
						_loading.value = Event(false)
					}

					override fun onError(t: Throwable) {
						Timber.e(t)
						_error.value = Event(t)
						_loading.value = Event(false)
					}

					override fun onComplete() {
						_loading.value = Event(false)
					}
				})
		}
	}

	fun fetchCoin(id: CoinId) {
		_loading.value = Event(true)

		addDisposable {
			getCoin(id)
				.subscribeWith(object : DisposableSingleObserver<CoinEntity>() {
					override fun onSuccess(coins: CoinEntity) {
						_coin.value = coins.toPresentation()
						_loading.value = Event(false)
					}

					override fun onStart() {
						super.onStart()
						_loading.value = Event(true)
					}

					override fun onError(t: Throwable) {
						Timber.e(t)
						_error.value = Event(t)
						_loading.value = Event(false)
					}
				})
		}
	}
}