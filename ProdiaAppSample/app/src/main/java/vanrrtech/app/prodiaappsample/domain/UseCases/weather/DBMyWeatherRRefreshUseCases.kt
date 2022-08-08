package vanrrtech.app.prodiaappsample.domain.UseCases.weather

import kotlinx.coroutines.launch
import vanrrtech.app.prodiaappsample.base_components.base_classes.BaseUseCase
import vanrrtech.app.prodiaappsample.data.SQDb.weather_data.WeatherDataDao
import vanrrtech.app.prodiaappsample.domain.data_model.weather.weather_data.WeatherData

class DBMyWeatherRefreshUseCases(
    val myApi : WeatherDataDao
) : BaseUseCase<WeatherData, Any>() {
    override fun setup(parameter: WeatherData) {
        super.setup(parameter)
        launch(coroutineContext) {
            execute {
                myApi.nukeTable()
                myApi.insertUsers(parameter)
                return@execute Unit
            }}
    }
}