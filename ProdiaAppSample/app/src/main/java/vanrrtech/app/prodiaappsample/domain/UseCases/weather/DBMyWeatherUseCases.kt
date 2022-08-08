package vanrrtech.app.prodiaappsample.domain.UseCases.weather

import kotlinx.coroutines.launch
import vanrrtech.app.prodiaappsample.base_components.base_classes.BaseUseCase
import vanrrtech.app.prodiaappsample.data.SQDb.weather_data.WeatherDataDao
import vanrrtech.app.prodiaappsample.domain.data_model.weather.weather_data.WeatherData

class DBMyWeatherUseCases(
    val myApi : WeatherDataDao
) : BaseUseCase<Any, WeatherData>() {

    override fun setup(parameter: Any) {
        super.setup(parameter)
        launch(coroutineContext) {
            execute {
                var data : WeatherData? = null
                data = myApi.loadAllUser()?.get(0)
                return@execute data
            }}
    }
}