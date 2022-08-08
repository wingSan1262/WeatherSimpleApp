package vanrrtech.app.prodiaappsample.data.remote_repository

import retrofit2.http.GET
import vanrrtech.app.prodiaappsample.domain.data_model.github.GithubUserItemResponse

interface GithubApiInterface {
    @GET("users")
    suspend fun getUsersList(): List<GithubUserItemResponse>

//    //https://api.github.com/search/
//    //q=user%3AwingSan1262&type=Users
//    @GET("search/users")
//    suspend fun searchUser(@Query("q") searchKey : String, @Query("type") type : String): SearchResult?
//
//    @GET("users/{USERNAME}/repos")
//    suspend fun getUserRepos(@Path("USERNAME") userName : String) : List<UserRepoDetails>
}