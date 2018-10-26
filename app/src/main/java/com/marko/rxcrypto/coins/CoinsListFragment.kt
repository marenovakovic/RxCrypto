package com.marko.rxcrypto.coins

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.marko.presentation.base.EventObserver
import com.marko.presentation.coins.CoinsViewModel
import com.marko.rxcrypto.R
import com.marko.rxcrypto.extensions.beVisibleIf
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_coins_list.*

class CoinsListFragment : DaggerFragment() {

	private val viewModel: CoinsViewModel by lazy {
		ViewModelProviders.of(activity !!).get(CoinsViewModel::class.java)
	}

	private val coinsAdapter: CoinsAdapter by lazy {
		CoinsAdapter {
			val action = CoinsListFragmentDirections.actionCoinsListFragmentToCoinDetailsFragment()
			action.setCoinId(it)
			Navigation.findNavController(activity !!, R.id.mainNavHostFragment).navigate(action)
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		viewModel.fetch()
		viewModel.coins.observe(this, Observer { coinsAdapter.coins = it })
		viewModel.loading.observe(this, EventObserver {
			coinsProgressBar.beVisibleIf(it)
		})
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? = inflater.inflate(R.layout.fragment_coins_list, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		coinsRecyclerView.apply {
			setHasFixedSize(true)
			adapter = coinsAdapter
			layoutManager = LinearLayoutManager(context).apply { isItemPrefetchEnabled = true }
		}
	}
}
