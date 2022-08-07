package vanrrtech.app.prodiaappsample.DependancyInjenction.App

import android.app.Application
import dagger.Module
import dagger.Provides
import vanrrtech.app.kompasgithubapp.app.DependancyInjenction.Activity.FragmentScope
import vanrrtech.app.kompasgithubapp.app.DependancyInjenction.AppScope
import vanrrtech.app.prodiaappsample.base_components.UtilServices.location.LocationService
import vanrrtech.app.prodiaappsample.data.SQDb.weather_data.WeatherDataDao
import vanrrtech.app.prodiaappsample.data.remote_repository.WeatherApiRetrofitClient
import vanrrtech.app.prodiaappsample.domain.UseCases.DBMyWeatherRefreshUseCases
import vanrrtech.app.prodiaappsample.domain.UseCases.DBMyWeatherUseCases
import vanrrtech.app.prodiaappsample.domain.UseCases.GetMyWeatherUseCases
import vanrrtech.app.prodiaappsample.domain.UseCases.LocationServiceUseCases
import vanrrtech.app.prodiaappsample.features.wheather_report.login.LoginVM
import vanrrtech.app.prodiaappsample.features.wheather_report.weather_list.view_model.WeatherListVM

@Module
class UseCasesModules() {

    @Provides
    @AppScope
    fun getMyWeatherUseCase(myApi : WeatherApiRetrofitClient) : GetMyWeatherUseCases =
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
}