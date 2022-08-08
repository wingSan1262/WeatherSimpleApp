package vanrrtech.app.prodiaappsample.domain.UseCases.github

import kotlinx.coroutines.launch
import vanrrtech.app.prodiaappsample.base_components.base_classes.BaseUseCase
import vanrrtech.app.prodiaappsample.data.remote_repository.RemoteApiRetrofitClient
import vanrrtech.app.prodiaappsample.domain.data_model.github.request.UserDetailRequest
import vanrrtech.app.prodiaappsample.domain.data_model.github.response.GithubUserItemResponse
import vanrrtech.app.prodiaappsample.domain.data_model.github.response.UserDetails
import vanrrtech.app.prodiaappsample.domain.data_model.github.response.UserRepoDetails
import vanrrtech.app.prodiaappsample.domain.data_model.weather.weather_data.MyWeatherParam
import vanrrtech.app.prodiaappsample.domain.data_model.weather.weather_data.WeatherData

class GetGithubUserRepoContentUseCase(
    val myApi : RemoteApiRetrofitClient
) : BaseUseCase<UserDetailRequest, List<UserRepoDetails>>() {

    override fun setup(parameter: UserDetailRequest) {
        super.setup(parameter)
        launch(coroutineContext) {
            execute {
                var data : List<UserRepoDetails>? = null
                myApi.getUserRepo(parameter).let {
                    data = it
                    return@execute data
                }
            }}
    }
}