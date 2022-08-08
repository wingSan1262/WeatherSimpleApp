package vanrrtech.app.prodiaappsample.domain.data_model.weather.daily_data_wheather

import com.google.gson.annotations.SerializedName

data class WeatherItemData (
    @SerializedName("id")
    val id : Int,
    @SerializedName("main")
    val mainDesc : String,
    @SerializedName("description")
    val detailDesc : String,
    @SerializedName("icon")
    val iconCode : String
    )