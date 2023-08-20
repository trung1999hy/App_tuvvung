package fus.com.vuatuvung.ui

import android.app.Application
import androidx.preference.PreferenceManager
import fus.com.vuatuvung.utils.ThemeManager
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class VuaTuVungApplication : Application() {

    companion object {
        var instance: VuaTuVungApplication? = null
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
