package vanrrtech.app.prodiaappsample.data.SQDb.weather_data

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import vanrrtech.app.prodiaappsample.domain.data_model.current_wheather.CurrentWeatherData
import java.lang.reflect.Type

@ProvidedTypeConverter
class CurrentWeatherTypeConverter {

    @TypeConverter
    fun fromCurrentWeatherData (weatherItem: CurrentWeatherData?): String? {
        if (weatherItem == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<CurrentWeatherData?>() {}.type
        return gson.toJson(weatherItem, type)
    }

    @TypeConverter
    fun toCurrentWeatherData (countryLangString: String?): CurrentWeatherData? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<CurrentWeatherData?>() {}.type
        return gson.fromJson<CurrentWeatherData>(countryLangString, type)
    }
}