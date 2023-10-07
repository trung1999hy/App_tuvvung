package com.tmt.vuaghepchu.utils

import android.content.Context
import android.content.SharedPreferences

class Preference(context: Context) {
    private val sharedPreferences: SharedPreferences
    private val PREFS_ACCOUNT = "PREFS_ACCOUNT"
    private val KEY_TOTAL_COIN = "KEY_TOTAL_COIN" // coin
    private val KEY_FIRST_INSTALL = "KEY_FIRST_INSTALL" // coin
    private val INT_ZERO = 0 // coin
    private val KEY_OLD_DATE = "KEY_OLD_DATE"
    private val KEY_TIME_MINI_GAME = "KEY_TIME_MINI_GAME"

    init {
        sharedPreferences = context.getSharedPreferences(PREFS_ACCOUNT, Context.MODE_PRIVATE)
    }

    var firstInstall: Boolean
        get() = sharedPreferences.getBoolean(KEY_FIRST_INSTALL, true)
        set(value) {
            sharedPreferences.edit().putBoolean(KEY_FIRST_INSTALL, value).apply()
        }

    fun setValueCoin(value: Int) {
        sharedPreferences.edit().putInt(KEY_TOTAL_COIN, value).apply()
    }

    fun getValueCoin(): Int {
        return sharedPreferences.getInt(KEY_TOTAL_COIN, INT_ZERO)
    }

    fun setValueDate(value: Int) {
        sharedPreferences.edit().putInt(KEY_OLD_DATE, value).apply()
    }

    fun getValueDate(): Int {
        return sharedPreferences.getInt(KEY_OLD_DATE, INT_ZERO)
    }

    fun setValueMiniGame(value: Int) {
        sharedPreferences.edit().putInt(KEY_TIME_MINI_GAME, value).apply()
    }

    fun getValueMiniGame(): Int {
        return sharedPreferences.getInt(KEY_TIME_MINI_GAME, INT_ZERO)
    }

    companion object {
        private var instance: Preference? = null
        fun buildInstance(context: Context): Preference? {
            if (instance == null) {
                instance = Preference(context)
            }
            return instance
        }
    }
}