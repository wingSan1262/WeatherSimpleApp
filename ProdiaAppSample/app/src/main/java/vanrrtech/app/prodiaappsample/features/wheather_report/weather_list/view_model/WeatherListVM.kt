package vanrrtech.app.prodiaappsample.features.wheather_report.weather_list.view_model


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import vanrrtech.app.prodiaappsample.domain.data_model.weather_data.WeatherData
import vanrrtech.app.prodiaappsample.base_components.entities.ResourceState
import vanrrtech.app.prodiaappsample.domain.UseCases.DBMyWeatherRefreshUseCases
import vanrrtech.app.prodiaappsample.domain.UseCases.DBMyWeatherUseCases
import vanrrtech.app.prodiaappsample.domain.UseCases.GetMyWeatherUseCases
import vanrrtech.app.prodiaappsample.domain.UseCases.LocationServiceUseCases
import vanrrtech.app.prodiaappsample.domain.data_model.weather_data.MyWeatherParam

class WeatherListVM(
    val weatherUseCases: GetMyWeatherUseCases,
    val offlineDbUseCases : DBMyWeatherUseCases,
    val dbRefreshUseCases : DBMyWeatherRefreshUseCases,
    val locationServiceUseCase: LocationServiceUseCases
    ) : ViewModel() {

    var currentWeatherParam = MyWeatherParam()
    fun fetchServerWeatherData (param: MyWeatherParam) {
        weatherUseCases.setup(param)
    }
    val weatherLiveData =
        Transformations.switchMap(weatherUseCases.currentData) { it ->
        val livedata = MutableLiveData<ResourceState<WeatherData>>()
            livedata.value = it
        when(it){
            is ResourceState.Success -> {
                it.body?.let { // store data
                    dbRefreshUseCases.setup(it) } }
            else->{} }
        return@switchMap livedata
    }

    val offlineWeatherLiveData = offlineDbUseCases.currentData
    fun fetchOfflineWeatherData () =
        offlineDbUseCases.setup(currentWeatherParam)

    val gpscoordinateLiveData = locationServiceUseCase.currentData
    fun fetchCoordinate(){
        locationServiceUseCase.setup(null)
    }
}