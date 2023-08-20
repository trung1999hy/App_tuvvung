package fus.com.vuatuvung.data.common

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferences @Inject constructor(private val application: Application) {

    private var sharedPreferences: SharedPreferences =
        application.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)

    fun initData(content: String) {
        sharedPreferences.edit().putString(CONTENT_DB, content).apply()
    }

    fun getData(default: String = "") = sharedPreferences.getString(CONTENT_DB, default)

    fun addLevel() {
        setLevel(getLevel() + 1)
    }

    fun getLevel(default: Int = 0) = sharedPreferences.getInt(LEVEL, default)

    private fun setLevel(value: Int) {
        sharedPreferences.edit().putInt(LEVEL, value).apply()
    }

    fun setEnableSound(value: Boolean) {
        sharedPreferences.edit().putBoolean(ENABLE_SOUND, value).apply()
    }

    fun isEnableSound(default: Boolean = true) = sharedPreferences.getBoolean(ENABLE_SOUND, default)

    fun setEnableSoundFX(value: Boolean) {
        sharedPreferences.edit().putBoolean(ENABLE_SOUND_FX, value).apply()
    }

    fun isEnableSoundFX(default: Boolean = true) =
        sharedPreferences.getBoolean(ENABLE_SOUND_FX, default)

    fun deletePref(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    companion object {
        private const val CONTENT_DB = "content_db"
        private const val LEVEL = "level"
        private const val ENABLE_SOUND = "ENABLE_SOUND"
        private const val ENABLE_SOUND_FX = "ENABLE_SOUND_FX"
        private const val PREFERENCES = "My_PREFERENCES"
    }
}