package com.tmt.vuaghepchu.ui

import android.app.Application
import androidx.preference.PreferenceManager
import com.tmt.vuaghepchu.utils.ThemeManager
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class VuaGhepTuApplication : Application() {

    companion object {
        var instance: VuaGhepTuApplication? = null
        fun context() = instance!!.applicationContext!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initTheme()
    }

    private fun initTheme() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        runCatching {
            ThemeManager.applyTheme(requireNotNull(preferences.getString("theme_key", "")))
        }.onFailure { exception ->
            Timber.e("Theme Manager: $exception")
        }
    }
}
