package vanrrtech.app.prodiaappsample.BaseClass

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import vanrrtech.app.kompasgithubapp.app.DependancyInjenction.Activity.FragmentModule
import vanrrtech.app.prodiaappsample.DependancyInjenction.Activity.ViewModelProducer.VmFactory
import vanrrtech.app.prodiaappsample.DependancyInjenction.Fragments.FragmentResultLauncher
import vanrrtech.app.prodiaappsample.TopActivity
import vanrrtech.app.prodiaappsample.UtilServices.location.ActivityLocationPermissionRequest
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {
    abstract fun onResult(result : ActivityResult)

    val hostActivity by lazy {this.activity as TopActivity}
    val activityComponent by lazy {(this.activity as BaseActivity<*, *>).activityComponent}

    @Inject lateinit var viewModelFactory: VmFactory
    @Inject @FragmentResultLauncher
    lateinit var resultLauncher: ActivityResultLauncher<Intent>

    val dialogFragmentNavigator by lazy { hostActivity.dialogFragmentNavigator }
    val screenNavigator by lazy { hostActivity.screenNavigator }
    val loginHandler by lazy { hostActivity.loginHandler }
    val imageLoader by lazy { hostActivity.imageLoader }
    val snackBarHandler by lazy { hostActivity.snackBarHandler }
    val viewBinderFactory by lazy { hostActivity.viewBinderFactory }
    val fragmentComponent by lazy {
        activityComponent.newFragmentComponent(FragmentModule(this))
    }

    lateinit var viewBinding : VB
    fun <T>bindThisView(modelClass : T, layoutInflater: LayoutInflater, container: ViewGroup){
        viewBinderFactory.bindViewFragment(modelClass!!::class.java, this, layoutInflater, container)
    }

    lateinit var viewModel : VM
    fun <T : ViewModel>getViewModel(modelClass : Class<T>){
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(modelClass) as VM
    }

    fun finish(){ screenNavigator.back() }
    fun requestPermission(){ ActivityLocationPermissionRequest.requestLocation(this) }
    fun openAppSetting(){hostActivity.openAppSetting()}
    fun dismissKey(){ hostActivity.dismissKey()}


}