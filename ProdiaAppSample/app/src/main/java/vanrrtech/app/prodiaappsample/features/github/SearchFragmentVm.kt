package vanrrtech.app.prodiaappsample.features.github


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import vanrrtech.app.prodiaappsample.base_components.entities.ResourceState
import vanrrtech.app.prodiaappsample.domain.UseCases.github.GetGithubUserListUseCase
import vanrrtech.app.prodiaappsample.domain.UseCases.github.GetOfflineGithubUserListUseCase
import vanrrtech.app.prodiaappsample.domain.UseCases.github.SearchUserGithubUseCase
import vanrrtech.app.prodiaappsample.domain.UseCases.github.UpdateOfflineGithubUserListUseCase
import vanrrtech.app.prodiaappsample.domain.data_model.github.request.SearchUserRequest
import vanrrtech.app.prodiaappsample.domain.data_model.github.response.GithubUserItemResponse

class SearchFragmentVm(
    val getGithubUserListUseCase: GetGithubUserListUseCase,
    val getOfflineGithubUserListUseCase: GetOfflineGithubUserListUseCase,
    val updateGithubUserListUseCase: UpdateOfflineGithubUserListUseCase,
    val searchUserGithubUseCase: SearchUserGithubUseCase,
) : ViewModel() {

    var isSearchQueried = false

    val githubUserLiveData = Transformations.switchMap(getGithubUserListUseCase.currentData) { it ->
        val livedata = MutableLiveData<ResourceState<List<GithubUserItemResponse>>>()
        livedata.value = it
        when(it) {
            is ResourceState.Success -> updateGithubUserList(it.body)
            else ->{}} //  do nothing
        return@switchMap livedata }
    fun fetchUserList() = getGithubUserListUseCase.setup(null)

    val updateOfflineUserLiveData = updateGithubUserListUseCase.currentData
    fun updateGithubUserList(data : List<GithubUserItemResponse>) =
        updateGithubUserListUseCase.setup(data)

    val offlineGithubUserLiveData = getOfflineGithubUserListUseCase.currentData
    fun fetchOfflineUserList() = getOfflineGithubUserListUseCase.setup(null)

    val searchResultLiveData = searchUserGithubUseCase.currentData
    fun fetchSearchResult (param : SearchUserRequest) =
        searchUserGithubUseCase.setup(param)

}