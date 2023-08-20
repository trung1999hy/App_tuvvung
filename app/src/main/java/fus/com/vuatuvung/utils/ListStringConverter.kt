package fus.com.vuatuvung.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class ListStringConverter {
    val gson = Gson()

    val type: Type = object : TypeToken<List<String?>?>() {}.type

    @TypeConverter
    fun fromWeatherDtoList(list: List<String?>?): String {
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun toWeatherDtoList(json: String?): List<String> {
        return gson.fromJson(json, type)
    }
}
