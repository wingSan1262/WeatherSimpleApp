package vanrrtech.app.prodiaappsample.data.SQDb.weather_data

import androidx.room.*
import vanrrtech.app.prodiaappsample.domain.data_model.WeatherData

@Dao
interface WeatherDataDao {

    @Query("Select * from weather_data_db")
    fun loadAllUser() : List<WeatherData?>?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers (movie : WeatherData)
    @Update
    fun updateUser (movie : WeatherData)
    @Delete
    fun deleteUser (movie : WeatherData)

    @Query("DELETE FROM weather_data_db")
    fun nukeTable()
}