package vanrrtech.app.prodiaappsample.features.github

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import vanrrtech.app.prodiaappsample.base_components.base_classes.BaseFragment
import vanrrtech.app.prodiaappsample.base_components.constants.PARAMETERS
import vanrrtech.app.prodiaappsample.base_components.entities.ResourceState
import vanrrtech.app.prodiaappsample.base_components.extensions.loadImage
import vanrrtech.app.prodiaappsample.base_components.extensions.setVisibility
import vanrrtech.app.prodiaappsample.databinding.UserDetailFragmentBinding
import vanrrtech.app.prodiaappsample.domain.data_model.github.request.UserDetailRequest
import vanrrtech.app.prodiaappsample.domain.data_model.github.response.GithubUserItemResponse
import vanrrtech.app.prodiaappsample.domain.data_model.github.response.UserDetails
import vanrrtech.app.prodiaappsample.domain.data_model.github.response.UserRepoDetails

class UserDetailFragment : BaseFragment<UserDetailFragmentBinding, UserDetailFragmentVm>() {

    override fun onResult(result: ActivityResult) {TODO("empty on result no implemented")}
    var repoAdapter : RepoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        fragmentComponent.inject(this)
        super.onCreate(savedInstanceState)
        getViewModel(UserDetailFragmentVm::class.java)
        (arguments?.getSerializable(PARAMETERS.USER_CLICKED_MODEL)
                as? GithubUserItemResponse)?.let {
                    viewModel.userClickedModel = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.let {
            bindThisView(this, layoutInflater, it)
        }
        return viewBinding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.onStart(viewLifecycleOwner)
        fetchAllData()
        initUi()
        observerData()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun fetchAllData(){
        viewModel.run{
            userClickedModel?.login?.let {
                UserDetailRequest(it).run{
                    fetchRepoList(this)
                    fetchUserData(this)
                }
            }
        }
    }
    fun initUi(){
        withBinding {
            repoAdapter = RepoAdapter();
            userRepoRv.apply {
                layoutManager = LinearLayoutManager(context,
                    LinearLayoutManager.VERTICAL,
                    false)
                isNestedScrollingEnabled = false
                adapter = repoAdapter
            }
        }
    }

    fun updateList(items: List<UserRepoDetails>){
        repoAdapter?.clearList()
        if(items.isEmpty() ||
            items.get(0).owner.login != viewModel.userClickedModel?.login) {
            return
        }
        lifecycleScope.launch {
                items.forEach {
                    repoAdapter?.insertAtTop(it)
                    delay(200)
                    withBinding { userRepoRv.smoothScrollToPosition(0) }
                }
        }
    }

    fun updateUserDetails(data: UserDetails) {
        withBinding {
            userImage.loadImage(data.avatarUrl)
            userName.text = data.name ?: data.login
            userAccountId.text = "@${data.login}"
            userDescTv.apply {
                data.bio?.let{ text = it } ?: run { setVisibility(false)}
            }
            followerTv.text = "${data.followers} Followers"
            followingTv.text = "${data.following} Following"
            locationTvTv.text = data.location ?: "Not Available"
            emailTv.text = data.email ?: "Not Available"
        }
    }

    fun observerData(){
        viewModel.userRepoLiveData.observe(viewLifecycleOwner){
            it.contentIfNotHandled?.apply {
                when (this){
                    is ResourceState.Success ->
                        updateList(body)
                    is ResourceState.Failure ->
                        snackBarHandler.showSnackBar("Oops, please check your internet connection")
                    else -> {} // do nothing
                }
            }
        }

        viewModel.userDetailLiveData.observe(viewLifecycleOwner){
            when (it){
                is ResourceState.Success -> {
                    updateUserDetails(it.body)
                }
                is ResourceState.Failure ->
                    snackBarHandler.showSnackBar("Oops, please check your internet connection")
                else -> {} // do nothing
            }
        }
    }
}