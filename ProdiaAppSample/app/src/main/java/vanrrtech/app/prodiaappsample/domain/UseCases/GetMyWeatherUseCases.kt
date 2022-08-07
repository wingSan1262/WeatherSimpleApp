package vanrrtech.app.prodiaappsample.domain.UseCases

import kotlinx.coroutines.launch
import vanrrtech.app.prodiaappsample.base_components.BaseUseCase
import vanrrtech.app.prodiaappsample.data.remote_repository.WeatherApiRetrofitClient
import vanrrtech.app.prodiaappsample.domain.data_model.weather_data.MyWeatherParam
import vanrrtech.app.prodiaappsample.domain.data_model.weather_data.WeatherData

class GetMyWeatherUseCases(
    val myApi : WeatherApiRetrofitClient
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
