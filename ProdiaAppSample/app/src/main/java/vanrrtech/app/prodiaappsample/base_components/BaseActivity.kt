package vanrrtech.app.prodiaappsample.base_components

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.provider.Settings
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import vanrrtech.app.kompasgithubapp.app.DependancyInjenction.Activity.ActivityComponent
import vanrrtech.app.kompasgithubapp.app.DependancyInjenction.Activity.ActivityModule
import vanrrtech.app.kompasgithubapp.app.DependancyInjenction.AppComponent
import vanrrtech.app.prodiaappsample.Application.MyApplication
import vanrrtech.app.prodiaappsample.DependancyInjenction.Activity.ViewBinderFactory.ViewBinderFactory
import vanrrtech.app.prodiaappsample.DependancyInjenction.Activity.ViewModelProducer.VmFactory
import vanrrtech.app.prodiaappsample.base_components.UtilServices.Imageloader
import vanrrtech.app.prodiaappsample.base_components.UtilServices.KeyboardDismisser
import vanrrtech.app.prodiaappsample.base_components.UtilServices.LoginHandler.LoginHandlerService
import vanrrtech.app.prodiaappsample.base_components.UtilServices.location.ActivityLocationPermissionRequest
import vanrrtech.app.prodiaappsample.base_components.UtilServices.snack_bar_handler.SnackBarHandler
import vanrrtech.app.prodiaappsample.base_components.dialog_fragments.dialog_navigator.DialogFragmentNavigator
import javax.inject.Inject

abstract class BaseActivity<VB : ViewBinding, VM : ViewModel> : AppCompatActivity() {

    abstract fun getActivity() : AppCompatActivity
    abstract fun onResult(result : ActivityResult)

    @Inject @vanrrtech.app.prodiaappsample.DependancyInjenction.Activity.ActivityResultLauncher
    lateinit var resultLauncher : ActivityResultLauncher<Intent>
    @Inject lateinit var loginHandler : LoginHandlerService
    @Inject lateinit var imageLoader: Imageloader
    @Inject lateinit var dialogFragmentNavigator: DialogFragmentNavigator
    @Inject lateinit var viewBinderFactory: ViewBinderFactory
    @Inject lateinit var viewModelFactory: VmFactory
    @Inject lateinit var keyboardDismisser: KeyboardDismisser

    lateinit var viewBinding : VB
    fun <T>bindThisView(host: T, layoutInflater: LayoutInflater, container: ViewGroup?){
        viewBinderFactory.bindViewActivity(host!!::class.java, this, layoutInflater)
    }

    lateinit var viewModel : VM
    fun getViewModel(){ viewModel = ViewModelProvider(this, viewModelFactory)
            .get(viewModel::class.java) }

    val snackBarHandler by lazy { SnackBarHandler(viewBinding.root)}
    val appComponent : AppComponent by lazy { (application as MyApplication).myAppComponent}
    val activityComponent : ActivityComponent by lazy {
        appComponent.newActivityComponent(ActivityModule(this, this))
    }
    fun requestPermission(){ ActivityLocationPermissionRequest.requestLocation(this) }
    fun dismissKey(){ keyboardDismisser.dismissSoftKey() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
    }

}