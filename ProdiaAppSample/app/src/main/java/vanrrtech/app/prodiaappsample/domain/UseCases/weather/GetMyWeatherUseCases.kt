package vanrrtech.app.prodiaappsample.domain.UseCases.weather

import kotlinx.coroutines.launch
import vanrrtech.app.prodiaappsample.base_components.base_classes.BaseUseCase
import vanrrtech.app.prodiaappsample.data.remote_repository.RemoteApiRetrofitClient
import vanrrtech.app.prodiaappsample.domain.data_model.weather.weather_data.MyWeatherParam
import vanrrtech.app.prodiaappsample.domain.data_model.weather.weather_data.WeatherData

class GetMyWeatherUseCases(
    val myApi : RemoteApiRetrofitClient
) : BaseUseCase<MyWeatherParam, WeatherData>() {

    override fun setup(parameter: MyWeatherParam) {
        super.setup(parameter)
        launch(coroutineContext) {
            execute {
                var data : WeatherData? = null
                myApi.getWeatherQueryApi(parameter).let {
                    data = it
                    return@execute data
                }
            }}
    }
}
