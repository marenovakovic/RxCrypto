package com.marko.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.WorkerThread
import androidx.core.content.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface PreferenceStorage {
	var lastCacheTime: Long
}

class SharedPreferenceStorage(
	context: Context
) : PreferenceStorage {

	companion object {
		const val PREFS_NAME = "coins"
		const val LAST_CACHE_TIME = "last_cache_time"
	}

	private val prefs =
		context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

	override var lastCacheTime: Long by LongPreference(prefs, LAST_CACHE_TIME, - 1)
}

private class LongPreference(
	private val preferences: SharedPreferences,
	private val name: String,
	private val defaultValue: Long
) : ReadWriteProperty<Any, Long> {

	@WorkerThread
	override fun getValue(thisRef: Any, property: KProperty<*>): Long =
		preferences.getLong(name, defaultValue)

	override fun setValue(thisRef: Any, property: KProperty<*>, value: Long) =
		preferences.edit { putLong(name, value) }
}