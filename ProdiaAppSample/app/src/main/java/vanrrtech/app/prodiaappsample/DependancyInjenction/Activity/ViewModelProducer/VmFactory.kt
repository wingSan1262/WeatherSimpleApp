package vanrrtech.app.prodiaappsample.DependancyInjenction.Activity.ViewModelProducer

import android.app.Application
import androidx.lifecycle.ViewModel

import androidx.lifecycle.ViewModelProvider
import vanrrtech.app.prodiaappsample.data.RepositoryInteractor.GetMyWeatherData.GetMyWeatherInteractor
import vanrrtech.app.prodiaappsample.base_components.UtilServices.location.LocationService
import vanrrtech.app.prodiaappsample.features.login.LoginVM
import vanrrtech.app.prodiaappsample.features.weather_list.view_model.WeatherListVM


class VmFactory(val mApplication: Application,
                val repo : GetMyWeatherInteractor,
                val locationService : LocationService
) :
    ViewModelProvider.Factory {
    private val mRepo by lazy {
        repo
    }

    fun <T : ViewModel?> getClassInstance(modelClass: Class<T>): T {
        when(modelClass){
            WeatherListVM::class.java -> {
                return WeatherListVM(mRepo, locationService) as T
            }
            LoginVM::class.java -> {
                return LoginVM(locationService) as T
            }
            else -> {
                throw Exception("no vm found")
            }
        }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return getClassInstance(modelClass)
    }

}