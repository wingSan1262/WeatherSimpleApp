package vanrrtech.app.prodiaappsample.data.SQDb.weather_item

import androidx.room.*
import vanrrtech.app.prodiaappsample.domain.data_model.daily_data_wheather.DailyWheatherItemData

@Dao
interface WeatherItemDao {

    @Query("Select * from weather_item_db")
    fun loadAllUser() : List<DailyWheatherItemData?>?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers (movie : DailyWheatherItemData)
    @Update
    fun updateUser (movie : DailyWheatherItemData)
    @Delete
    fun deleteUser (movie : DailyWheatherItemData)

    @Query("DELETE FROM weather_item_db")
    fun nukeTable()
}