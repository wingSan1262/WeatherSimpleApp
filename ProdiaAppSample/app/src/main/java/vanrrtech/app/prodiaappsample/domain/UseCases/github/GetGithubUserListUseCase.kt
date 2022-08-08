package vanrrtech.app.prodiaappsample.domain.UseCases.github

import kotlinx.coroutines.launch
import vanrrtech.app.prodiaappsample.base_components.base_classes.BaseUseCase
import vanrrtech.app.prodiaappsample.data.remote_repository.RemoteApiRetrofitClient
import vanrrtech.app.prodiaappsample.domain.data_model.github.GithubUserItemResponse
import vanrrtech.app.prodiaappsample.domain.data_model.weather.weather_data.MyWeatherParam
import vanrrtech.app.prodiaappsample.domain.data_model.weather.weather_data.WeatherData

class GetGithubUserListUseCase(
    val myApi : RemoteApiRetrofitClient
) : BaseUseCase<Any?, List<GithubUserItemResponse>>() {

    override fun setup(parameter: Any?) {
        super.setup(parameter)
        launch(coroutineContext) {
            execute {
                var data : List<GithubUserItemResponse>? = null
                myApi.getUserList().let {
                    data = it
                    return@execute data
                }
            }}
    }
}
