package vanrrtech.app.prodiaappsample.data.SQDb.weather_data

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import vanrrtech.app.prodiaappsample.domain.data_model.weather.daily_data_wheather.DailyWheatherItemData
import java.lang.reflect.Type

@ProvidedTypeConverter
class DailyWeatherListTypeConverter  {

    @TypeConverter
    fun fromWeatherDataList (weatherItem: List<DailyWheatherItemData>?): String? {
        if (weatherItem == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<DailyWheatherItemData>?>() {}.type
        return gson.toJson(weatherItem, type)
    }

    @TypeConverter
    fun toWeatherDataList (countryLangString: String?): List<DailyWheatherItemData>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<DailyWheatherItemData>?>() {}.type
        return gson.fromJson<List<DailyWheatherItemData>>(countryLangString, type)
    }
}