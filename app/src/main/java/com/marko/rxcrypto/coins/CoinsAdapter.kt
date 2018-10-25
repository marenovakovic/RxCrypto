package com.marko.rxcrypto.coins

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marko.domain.usecase.CoinId
import com.marko.presentation.entities.Coin
import com.marko.rxcrypto.R
import com.marko.rxcrypto.extensions.inflate
import kotlinx.android.synthetic.main.list_item_coin.view.*

class CoinsAdapter(
	private val onClick: (id: CoinId) -> Unit
) : ListAdapter<Coin, CoinsAdapter.CoinHolder>(DiffCallbacks) {

	var coins = listOf<Coin>()
	set(value) {
		submitList(value)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinHolder {
		val view = parent.inflate(R.layout.list_item_coin)
		return CoinHolder(view)
	}

	override fun onBindViewHolder(holder: CoinHolder, position: Int) =
		holder.bind(getItem(position))

	inner class CoinHolder(view: View) : RecyclerView.ViewHolder(view) {

		fun bind(coin: Coin) {
			itemView.listItemCoinName.text = coin.name
			itemView.setOnClickListener {
				onClick(coin.id)
			}
		}
	}
}

object DiffCallbacks : DiffUtil.ItemCallback<Coin>() {

	override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean =
		oldItem.id == newItem.id

	override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean =
		oldItem == newItem
}