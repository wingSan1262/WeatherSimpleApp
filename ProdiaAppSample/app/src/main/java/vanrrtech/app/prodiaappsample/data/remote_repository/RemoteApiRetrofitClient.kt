package vanrrtech.app.prodiaappsample.data.remote_repository

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vanrrtech.app.prodiaappsample.domain.data_model.github.request.SearchUserRequest
import vanrrtech.app.prodiaappsample.domain.data_model.github.request.UserDetailRequest
import vanrrtech.app.prodiaappsample.domain.data_model.github.response.GithubUserItemResponse
import vanrrtech.app.prodiaappsample.domain.data_model.github.response.SearchResult
import vanrrtech.app.prodiaappsample.domain.data_model.github.response.UserDetails
import vanrrtech.app.prodiaappsample.domain.data_model.github.response.UserRepoDetails
import vanrrtech.app.prodiaappsample.domain.data_model.weather.weather_data.MyWeatherParam
import vanrrtech.app.prodiaappsample.domain.data_model.weather.weather_data.WeatherData

class RemoteApiRetrofitClient {

    companion object{
        val BASE_URL_WEATHER = "https://api.openweathermap.org/"
        val BASE_URL_GITHUB = "https://api.github.com/"
    }

    private val logging by lazy { HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY) }
    private  val mClient by lazy {
        OkHttpClient.Builder().addInterceptor(logging)
    }
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_WEATHER)
            .addConverterFactory(GsonConverterFactory.create())
            .client(mClient.build())
            .build()
    }

    fun getWeatherApiRetrofit(): WeatherApiService {
        return retrofit
            .newBuilder()
            .baseUrl(BASE_URL_WEATHER)
            .build()
            .create(WeatherApiService::class.java)
    }

    fun getGithubRetrofit(): GithubApiInterface {
        return retrofit
            .newBuilder()
            .baseUrl(BASE_URL_GITHUB)
            .build()
            .create(GithubApiInterface::class.java)
    }

    suspend fun getWeatherQueryApi(parameter: MyWeatherParam): WeatherData {
        return getWeatherApiRetrofit().getWeatherData(
            parameter.lat,
            parameter.lon,
            parameter.exclude,
            parameter.apikey)
    }

    suspend fun getUserList(): List<GithubUserItemResponse> {
        return getGithubRetrofit().getUsersList()
    }

    suspend fun searchUserResult(request: SearchUserRequest): SearchResult? {
        return getGithubRetrofit().searchUser(request.query, request.type)
    }

    suspend fun getUserDetail(request: UserDetailRequest): UserDetails? {
        return getGithubRetrofit().getUserDetails(request.userName)
    }

    suspend fun getUserRepo(request: UserDetailRequest): List<UserRepoDetails> {
        return getGithubRetrofit().getUserRepos(request.userName)
    }

}