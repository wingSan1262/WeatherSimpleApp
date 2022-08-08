package vanrrtech.app.prodiaappsample.features.wheather_report.weather_list.view_model


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import vanrrtech.app.prodiaappsample.domain.data_model.weather.weather_data.WeatherData
import vanrrtech.app.prodiaappsample.base_components.entities.ResourceState
import vanrrtech.app.prodiaappsample.domain.UseCases.github.GetGithubUserListUseCase
import vanrrtech.app.prodiaappsample.domain.UseCases.weather.DBMyWeatherRefreshUseCases
import vanrrtech.app.prodiaappsample.domain.UseCases.weather.DBMyWeatherUseCases
import vanrrtech.app.prodiaappsample.domain.UseCases.weather.GetMyWeatherUseCases
import vanrrtech.app.prodiaappsample.domain.UseCases.weather.LocationServiceUseCases
import vanrrtech.app.prodiaappsample.domain.data_model.github.response.GithubUserItemResponse
import vanrrtech.app.prodiaappsample.domain.data_model.weather.weather_data.MyWeatherParam

class WeatherListVM(
    val weatherUseCases: GetMyWeatherUseCases,
    val offlineDbUseCases: DBMyWeatherUseCases,
    val dbRefreshUseCases: DBMyWeatherRefreshUseCases,
    val locationServiceUseCase: LocationServiceUseCases,
    val githubUserListUseCase: GetGithubUserListUseCase
) : ViewModel() {

    var currentWeatherParam = MyWeatherParam()
    fun fetchServerWeatherData (param: MyWeatherParam) {
        weatherUseCases.setup(param)
    }
    val weatherLiveData =
        Transformations.switchMap(weatherUseCases.currentData) { it ->
            val livedata = MutableLiveData<ResourceState<WeatherData>>()
            it.contentIfNotHandled?.let{
                livedata.value = it
                when(it){
                    is ResourceState.Success -> {
                        it.body?.let { // store data
                            dbRefreshUseCases.setup(it) } }
                    else->{} }
            }
        return@switchMap livedata
    }

    val offlineWeatherLiveData = offlineDbUseCases.currentData
    fun fetchOfflineWeatherData () =
        offlineDbUseCases.setup(currentWeatherParam)

    val gpscoordinateLiveData = locationServiceUseCase.currentData
    fun fetchCoordinate(){
        locationServiceUseCase.setup(null)
    }

    val githubUserListLiveData =
    Transformations.switchMap(githubUserListUseCase.currentData) { it ->
        val livedata = MutableLiveData<ResourceState<List<GithubUserItemResponse>>>()
        it.contentIfNotHandled?.let{
            when(it){
                is ResourceState.Success -> {
                    Log.i("tes", "tess")
                }
                else->{}
            }
            livedata.value = it
        }
        return@switchMap livedata
    }
    fun fetchGithubUserList(){
        githubUserListUseCase.setup(null)
    }
}