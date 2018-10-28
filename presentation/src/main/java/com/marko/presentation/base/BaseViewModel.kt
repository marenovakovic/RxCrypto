package com.marko.presentation.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

	private val disposables = CompositeDisposable()

	@Synchronized
	protected fun addDisposable(source: () -> Disposable) {
		disposables.add(source())
	}

	override fun onCleared() {
		super.onCleared()

		if (! disposables.isDisposed) {
			disposables.dispose()
		}
	}
}