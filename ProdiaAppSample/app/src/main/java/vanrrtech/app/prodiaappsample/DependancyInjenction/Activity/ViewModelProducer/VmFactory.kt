package vanrrtech.app.prodiaappsample.DependancyInjenction.Activity.ViewModelProducer

import android.app.Application
import androidx.lifecycle.ViewModel

import androidx.lifecycle.ViewModelProvider
import vanrrtech.app.prodiaappsample.base_components.UtilServices.location.LocationService
import vanrrtech.app.prodiaappsample.domain.UseCases.github.GetGithubUserListUseCase
import vanrrtech.app.prodiaappsample.domain.UseCases.github.GetOfflineGithubUserListUseCase
import vanrrtech.app.prodiaappsample.domain.UseCases.weather.DBMyWeatherRefreshUseCases
import vanrrtech.app.prodiaappsample.domain.UseCases.weather.DBMyWeatherUseCases
import vanrrtech.app.prodiaappsample.domain.UseCases.weather.GetMyWeatherUseCases
import vanrrtech.app.prodiaappsample.domain.UseCases.weather.LocationServiceUseCases
import vanrrtech.app.prodiaappsample.features.wheather_report.login.LoginVM
import vanrrtech.app.prodiaappsample.features.wheather_report.weather_list.view_model.WeatherListVM


class VmFactory(
    val mApplication: Application,
    val locationService: LocationService,
    val weatherUseCases: GetMyWeatherUseCases,
    val offlineDbUseCases: DBMyWeatherUseCases,
    val dbRefreshUseCases: DBMyWeatherRefreshUseCases,
    val locationServiceUseCase: LocationServiceUseCases,
    val githubUserListUseCase: GetGithubUserListUseCase,
    val getOfflineGithubUserListUseCase: GetOfflineGithubUserListUseCase,
    val updateOfflineGithubUserListUseCase: GetOfflineGithubUserListUseCase
) :
    ViewModelProvider.Factory {

    fun <T : ViewModel?> getClassInstance(modelClass: Class<T>): T {
        when(modelClass){
            WeatherListVM::class.java -> {
                return WeatherListVM(
                    weatherUseCases,
                    offlineDbUseCases ,
                    dbRefreshUseCases,
                    locationServiceUseCase,
                    githubUserListUseCase
                ) as T
            }
            LoginVM::class.java -> {
                return LoginVM(locationService) as T
            }
            else -> {
                throw Exception("no vm found")
            }
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return getClassInstance(modelClass)
    }

}