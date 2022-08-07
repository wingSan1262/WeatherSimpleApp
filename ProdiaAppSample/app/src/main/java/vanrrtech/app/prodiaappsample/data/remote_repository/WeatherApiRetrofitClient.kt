package vanrrtech.app.prodiaappsample.data.remote_repository

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vanrrtech.app.prodiaappsample.domain.data_model.weather_data.MyWeatherParam
import vanrrtech.app.prodiaappsample.domain.data_model.weather_data.WeatherData

class WeatherApiRetrofitClient {

    companion object{
        val BASE_URL = "https://api.openweathermap.org/"
    }

    private val logging by lazy { HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY) }
    private  val mClient by lazy {
        OkHttpClient.Builder().addInterceptor(logging)
    }
    private  val userService : WeatherApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mClient.build())
            .build()
            .create(WeatherApiService::class.java)
    }

    suspend fun getWeatherQueryApi(parameter: MyWeatherParam): WeatherData {
        return userService.getWeatherData(
            parameter.lat,
            parameter.lon,
            parameter.exclude,
            parameter.apikey)
    }




}