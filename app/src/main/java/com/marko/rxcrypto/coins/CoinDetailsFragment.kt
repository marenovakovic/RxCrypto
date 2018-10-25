package com.marko.rxcrypto.coins

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.marko.domain.usecase.CoinId
import com.marko.presentation.coins.CoinsViewModel
import com.marko.presentation.entities.Coin
import com.marko.rxcrypto.R
import kotlinx.android.synthetic.main.fragment_coin_details.*

class CoinDetailsFragment : Fragment() {

	private val viewModel: CoinsViewModel by lazy {
		ViewModelProviders.of(activity !!).get(CoinsViewModel::class.java)
	}

	private val coinId: CoinId by lazy {
		CoinDetailsFragmentArgs.fromBundle(arguments).coinId
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? = inflater.inflate(R.layout.fragment_coin_details, container, false)

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		viewModel.fetchCoin(coinId)
		viewModel.coin.observe(this, Observer(::handleCoin))
	}

	private fun handleCoin(coin: Coin) {
		coinDetailsId.text = coin.id.toString()
		coinDetailsSymbol.text = coin.symbol
		coinDetailName.text = coin.name
	}
}
