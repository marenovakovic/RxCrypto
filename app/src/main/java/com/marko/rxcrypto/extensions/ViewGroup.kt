package com.marko.rxcrypto.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(resource: Int): View =
	LayoutInflater.from(context).inflate(resource, this, false)