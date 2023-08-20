package fus.com.vuatuvung.data.repository.sharedrepositoy

interface SharedRepository {

    fun initData(value: String)

    fun getInitData(): String

    fun getLevel(): Int

    fun addLevel()

    fun isEnableSound(): Boolean

    fun setEnableSound(value: Boolean)

    fun isEnableSoundFX(): Boolean

    fun setEnableSoundFX(value: Boolean)

    fun deletePref(key: String)
}