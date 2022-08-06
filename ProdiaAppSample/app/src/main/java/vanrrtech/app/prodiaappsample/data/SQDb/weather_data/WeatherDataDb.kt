package vanrrtech.app.prodiaappsample.data.SQDb.weather_data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import vanrrtech.app.prodiaappsample.domain.data_model.WeatherData

@Database(entities = [WeatherData::class], exportSchema = false, version = 1)
@TypeConverters(CurrentWeatherTypeConverter::class, DailyWeatherListTypeConverter::class)
public abstract class WeatherDataDb() : RoomDatabase() {

    companion object {
        lateinit var appContext : Context
        val DB_NAME: String = "weather_data_db"
        val instance: WeatherDataDb by lazy {
            Room.databaseBuilder(
                appContext,
                WeatherDataDb::class.java,
                DB_NAME
            )
                .addTypeConverter(CurrentWeatherTypeConverter())
                .addTypeConverter(DailyWeatherListTypeConverter())
                .fallbackToDestructiveMigration()
                .build()
        }

        fun getInstance(context: Context): WeatherDataDb {
            appContext = context
            return instance
        }
    }

    public abstract fun weatherDataDao(): WeatherDataDao
}