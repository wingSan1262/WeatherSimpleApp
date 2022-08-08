package vanrrtech.app.prodiaappsample.DependancyInjenction.App

import dagger.Module
import dagger.Provides
import vanrrtech.app.kompasgithubapp.app.DependancyInjenction.AppScope
import vanrrtech.app.prodiaappsample.base_components.UtilServices.location.LocationService
import vanrrtech.app.prodiaappsample.data.SQDb.github.UserListDao
import vanrrtech.app.prodiaappsample.data.SQDb.weather_data.WeatherDataDao
import vanrrtech.app.prodiaappsample.data.remote_repository.RemoteApiRetrofitClient
import vanrrtech.app.prodiaappsample.domain.UseCases.github.GetGithubUserListUseCase
import vanrrtech.app.prodiaappsample.domain.UseCases.github.GetOfflineGithubUserListUseCase
import vanrrtech.app.prodiaappsample.domain.UseCases.github.SearchUserGithubUseCase
import vanrrtech.app.prodiaappsample.domain.UseCases.github.UpdateOfflineGithubUserListUseCase
import vanrrtech.app.prodiaappsample.domain.UseCases.weather.DBMyWeatherRefreshUseCases
import vanrrtech.app.prodiaappsample.domain.UseCases.weather.DBMyWeatherUseCases
import vanrrtech.app.prodiaappsample.domain.UseCases.weather.GetMyWeatherUseCases
import vanrrtech.app.prodiaappsample.domain.UseCases.weather.LocationServiceUseCases

@Module
class UseCasesModules() {

    @Provides
    @AppScope
    fun getMyWeatherUseCase(myApi : RemoteApiRetrofitClient) : GetMyWeatherUseCases =
        GetMyWeatherUseCases(myApi)

    @Provides
    @AppScope
    fun getDBMyWeatherUseCase(myApi : WeatherDataDao) : DBMyWeatherUseCases =
        DBMyWeatherUseCases(myApi)

    @Provides
    @AppScope
    fun getDBMyWeatherRefreshUseCases(myApi : WeatherDataDao) : DBMyWeatherRefreshUseCases =
        DBMyWeatherRefreshUseCases(myApi)

    @Provides
    @AppScope
    fun getLocationServiceUseCases(myApi : LocationService) : LocationServiceUseCases =
        LocationServiceUseCases(myApi)

    @Provides
    @AppScope
    fun getGithubUsers(myApi : RemoteApiRetrofitClient) : GetGithubUserListUseCase =
        GetGithubUserListUseCase(myApi)

    @Provides
    @AppScope
    fun updateOfflineGithubUsers(myApi : UserListDao) : UpdateOfflineGithubUserListUseCase =
        UpdateOfflineGithubUserListUseCase(myApi)

    @Provides
    @AppScope
    fun getOfflineGithubUsers(myApi : UserListDao) : GetOfflineGithubUserListUseCase =
        GetOfflineGithubUserListUseCase(myApi)

    @Provides
    @AppScope
    fun searchUser(myApi : RemoteApiRetrofitClient) : SearchUserGithubUseCase =
        SearchUserGithubUseCase(myApi)
}