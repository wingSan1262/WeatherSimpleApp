package vanrrtech.app.kompasgithubapp.app.DependancyInjenction.Activity

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import vanrrtech.app.prodiaappsample.BaseClass.BaseFragment
import vanrrtech.app.prodiaappsample.DependancyInjenction.Activity.ViewModelProducer.VmFactory
import vanrrtech.app.prodiaappsample.DependancyInjenction.Fragments.FragmentResultLauncher
import vanrrtech.app.prodiaappsample.features.login.LoginVM
import vanrrtech.app.prodiaappsample.features.weather_list.view_model.WeatherListVM

@Module
class FragmentModule (val fragment : Fragment) {

    @Provides
    @FragmentScope
    fun getVMWeather(vmFactory: VmFactory) : WeatherListVM =
        ViewModelProvider(fragment, vmFactory).get(WeatherListVM::class.java)

    @Provides
    @FragmentScope
    fun getVMLogin(vmFactory: VmFactory) : LoginVM =
        ViewModelProvider(fragment, vmFactory).get(LoginVM::class.java)

    @JvmName("getFragment1")
    @Provides
    fun getFragment() : Fragment = fragment

    @Provides
    @FragmentResultLauncher
    fun getResultLauncher(fragment: Fragment) : ActivityResultLauncher<Intent> =
        fragment.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            (fragment as BaseFragment<*, *>).onResult(result)
        }
}