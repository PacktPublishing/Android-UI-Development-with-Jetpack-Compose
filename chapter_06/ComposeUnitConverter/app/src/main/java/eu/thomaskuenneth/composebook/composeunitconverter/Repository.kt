package eu.thomaskuenneth.composebook.composeunitconverter

import android.content.Context
import androidx.preference.PreferenceManager

class Repository(context: Context) {

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    fun getInt(key: String, default: Int) = prefs.getInt(key, default)

    fun putInt(key: String, value: Int) {
        prefs.edit().putInt(key, value).apply()
    }

    fun getString(key: String, default: String) = prefs.getString(key, default)

    fun putString(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }
}