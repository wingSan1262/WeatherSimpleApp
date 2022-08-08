package vanrrtech.app.prodiaappsample.domain.UseCases.github

import kotlinx.coroutines.launch
import vanrrtech.app.prodiaappsample.base_components.base_classes.BaseUseCase
import vanrrtech.app.prodiaappsample.data.SQDb.github.UserListDao
import vanrrtech.app.prodiaappsample.data.remote_repository.RemoteApiRetrofitClient
import vanrrtech.app.prodiaappsample.domain.data_model.github.response.GithubUserItemResponse
import vanrrtech.app.prodiaappsample.domain.data_model.weather.weather_data.MyWeatherParam
import vanrrtech.app.prodiaappsample.domain.data_model.weather.weather_data.WeatherData

class UpdateOfflineGithubUserListUseCase(
    val myApi : UserListDao
) : BaseUseCase<List<GithubUserItemResponse>, Boolean>() {
    override fun setup(parameter: List<GithubUserItemResponse>) {
        super.setup(parameter)
        launch(coroutineContext) {
            execute {
                try {
                    myApi.nukeTable()
                    parameter.forEach {
                        myApi.insertUsers(it)
                    }
                } catch (e : Throwable){return@execute false}
                return@execute true
            }}
    }
}
