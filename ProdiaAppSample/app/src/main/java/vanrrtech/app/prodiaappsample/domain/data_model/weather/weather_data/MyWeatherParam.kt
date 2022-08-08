package vanrrtech.app.prodiaappsample.domain.data_model.weather.weather_data

import vanrrtech.app.prodiaappsample.base_components.base_interface.BaseModel

data class MyWeatherParam
    (
    val lat : String = "",
    val lon : String = "",
    val exclude : String = "",
    val apikey : String = ""
    ) : BaseModel

