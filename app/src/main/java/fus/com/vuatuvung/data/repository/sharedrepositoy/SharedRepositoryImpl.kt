package fus.com.vuatuvung.data.repository.sharedrepositoy

import fus.com.vuatuvung.data.common.SharedPreferences
import javax.inject.Inject

class SharedRepositoryImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    SharedRepository {

    override fun initData(value: String) {
        sharedPreferences.initData(value)
    }

    override fun getInitData(): String = sharedPreferences.getData() ?: ""

    override fun getLevel() : Int = sharedPreferences.getLevel()

    override fun addLevel(){
        sharedPreferences.addLevel()
    }

    override fun isEnableSound(): Boolean = sharedPreferences.isEnableSound()

    override fun setEnableSound(value: Boolean) {
        sharedPreferences.setEnableSound(value)
    }

    override fun isEnableSoundFX(): Boolean = sharedPreferences.isEnableSoundFX()

    override fun setEnableSoundFX(value: Boolean) {
        sharedPreferences.setEnableSoundFX(value)
    }

    override fun deletePref(key: String) {
        sharedPreferences.deletePref(key)
    }

}