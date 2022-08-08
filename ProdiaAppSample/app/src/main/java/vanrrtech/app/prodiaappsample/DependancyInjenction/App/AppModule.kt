package vanrrtech.app.kompasgithubapp.app.DependancyInjenction

import android.app.Application
import dagger.Module
import dagger.Provides
import vanrrtech.app.prodiaappsample.DependancyInjenction.Activity.ViewBinderFactory.ViewBinderFactory
import vanrrtech.app.prodiaappsample.DependancyInjenction.Activity.ViewModelProducer.VmFactory
import vanrrtech.app.prodiaappsample.data.SQDb.weather_data.WeatherDataDao
import vanrrtech.app.prodiaappsample.data.SQDb.weather_data.WeatherDataDb
import vanrrtech.app.prodiaappsample.data.remote_repository.RemoteApiRetrofitClient
import vanrrtech.app.prodiaappsample.base_components.UtilServices.location.LocationService
import vanrrtech.app.prodiaappsample.base_components.UtilServices.shared_preference.SharedPreferenceService
import vanrrtech.app.prodiaappsample.data.SQDb.github.GithubUserDb
import vanrrtech.app.prodiaappsample.data.SQDb.github.UserListDao
import vanrrtech.app.prodiaappsample.domain.UseCases.github.GetGithubUserListUseCase
import vanrrtech.app.prodiaappsample.domain.UseCases.github.GetOfflineGithubUserListUseCase
import vanrrtech.app.prodiaappsample.domain.UseCases.weather.DBMyWeatherRefreshUseCases
import vanrrtech.app.prodiaappsample.domain.UseCases.weather.DBMyWeatherUseCases
import vanrrtech.app.prodiaappsample.domain.UseCases.weather.GetMyWeatherUseCases
import vanrrtech.app.prodiaappsample.domain.UseCases.weather.LocationServiceUseCases

@Module
class AppModule(val application: Application) {

    @Provides
    @AppScope
    fun getClient(): RemoteApiRetrofitClient {
        return RemoteApiRetrofitClient()
    }

    @Provides
    @AppScope
    fun getWeatherDataDB(application: Application): WeatherDataDb {
        return WeatherDataDb.getInstance(application.applicationContext)
    }

    @Provides
    @AppScope
    fun getWeatherDataDbDao(db : WeatherDataDb): WeatherDataDao {
        return db.weatherDataDao()
    }

    @Provides
    @AppScope
    fun getUserListDataDB(application: Application): GithubUserDb {
        return GithubUserDb.getInstance(application.applicationContext)
    }

    @Provides
    @AppScope
    fun getUserListDataDBDao(db : GithubUserDb): UserListDao {
        return db.userItemDao()
    }

    @Provides
    fun application() = application


    @Provides
    @AppScope
    fun getLocationServiceProvider(application: Application) : LocationService = LocationService(application)


    @Provides
    @AppScope
    fun getSharedPreferenceService(): SharedPreferenceService = SharedPreferenceService(application)

    @Provides
    @AppScope
    fun getVmFactory(mApplication: Application,
                     locationService : LocationService,
                     weatherUseCases: GetMyWeatherUseCases,
                     offlineDbUseCases : DBMyWeatherUseCases,
                     dbRefreshUseCases : DBMyWeatherRefreshUseCases,
                     locationServiceUseCase: LocationServiceUseCases,
                     githubUserListUseCase: GetGithubUserListUseCase,
                     getOfflineGithubUserListUseCase: GetOfflineGithubUserListUseCase,
                     updateOfflineGithubUserListUseCase: GetOfflineGithubUserListUseCase
    ) : VmFactory =
        VmFactory(
            mApplication,
            locationService,
            weatherUseCases,
            offlineDbUseCases,
            dbRefreshUseCases,
            locationServiceUseCase,
            githubUserListUseCase,
            getOfflineGithubUserListUseCase,
            updateOfflineGithubUserListUseCase
        )


    @Provides
    @AppScope
    fun getViewBinderFactory() : ViewBinderFactory = ViewBinderFactory()



}