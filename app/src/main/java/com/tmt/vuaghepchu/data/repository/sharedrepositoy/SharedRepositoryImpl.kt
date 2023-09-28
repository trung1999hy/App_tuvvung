package com.tmt.vuaghepchu.data.repository.sharedrepositoy

import com.tmt.vuaghepchu.data.common.AppSharedPreferences
import javax.inject.Inject

class SharedRepositoryImpl @Inject constructor(private val appSharedPreferences: AppSharedPreferences) :
    SharedRepository {

    override fun initData(value: String) {
        appSharedPreferences.initData(value)
    }

    override fun getInitData(): String = appSharedPreferences.getData() ?: ""

    override fun getLevel() : Int = appSharedPreferences.getLevel()

    override fun addLevel(){
        appSharedPreferences.addLevel()
    }

    override fun isEnableSound(): Boolean = appSharedPreferences.isEnableSound()

    override fun setEnableSound(value: Boolean) {
        appSharedPreferences.setEnableSound(value)
    }

    override fun isEnableSoundFX(): Boolean = appSharedPreferences.isEnableSoundFX()

    override fun setEnableSoundFX(value: Boolean) {
        appSharedPreferences.setEnableSoundFX(value)
    }

    override fun deletePref(key: String) {
        appSharedPreferences.deletePref(key)
    }

}