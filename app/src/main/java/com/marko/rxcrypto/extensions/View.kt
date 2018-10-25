package com.marko.rxcrypto.extensions

import android.view.View

fun View.beVisibleIf(condition: Boolean) {
	if (condition) {
		visibility = View.VISIBLE
	} else {
		visibility = View.GONE
	}
}