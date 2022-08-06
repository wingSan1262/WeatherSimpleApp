package vanrrtech.app.kompasgithubapp.app.DependancyInjenction.Activity

import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides
import vanrrtech.app.prodiaappsample.base_components.BaseActivity
import vanrrtech.app.prodiaappsample.base_components.UtilServices.Imageloader
import vanrrtech.app.prodiaappsample.base_components.UtilServices.KeyboardDismisser
import vanrrtech.app.prodiaappsample.base_components.UtilServices.LoginHandler.LoginHandlerService
import vanrrtech.app.prodiaappsample.base_components.UtilServices.UnixDateConverter
import vanrrtech.app.prodiaappsample.base_components.UtilServices.screen_navigator.ScreenNavigator
import vanrrtech.app.prodiaappsample.base_components.UtilServices.shared_preference.SharedPreferenceService
import vanrrtech.app.prodiaappsample.base_components.dialog_fragments.DialogFragmentFactory
import vanrrtech.app.prodiaappsample.base_components.dialog_fragments.dialog_navigator.DialogFragmentNavigator
import vanrrtech.app.prodiaappsample.base_components.dialog_fragments.dialog_navigator.DialogStringBundleFactory
import vanrrtech.app.prodiaappsample.features.weather_list.view.WeatherListAdapter

@Module
class ActivityModule (val activity: AppCompatActivity, val context : Context) {

    @Provides
    fun activity() = activity

    @Provides
    fun context(): Context = context

    @Provides
    fun layoutInflater() = LayoutInflater.from(activity)

    @Provides
    fun fragmentManager() : FragmentManager = activity.supportFragmentManager

    @Provides
    @vanrrtech.app.prodiaappsample.DependancyInjenction.Activity.ActivityResultLauncher
    fun getResultLauncher(activity: AppCompatActivity): ActivityResultLauncher<Intent> =
        activity.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            (activity as BaseActivity<*, *>).onResult(result)
        }


    @Provides
    fun getDateConverterService(): UnixDateConverter = UnixDateConverter()

    @Provides
    fun getRvAdapter(
        context: Context,
        dateConverter: UnixDateConverter,
        imageloader: Imageloader
    ): WeatherListAdapter = WeatherListAdapter(
        context, dateConverter, imageloader
    )

    @ActivityScope
    @Provides
    fun getLoginHandler(sharedPreferences: SharedPreferenceService): LoginHandlerService =
        LoginHandlerService(sharedPreferences)

    @Provides
    @ActivityScope
    fun getImageLoader(): Imageloader = Imageloader(context)

    @Provides
    fun getDialogFactory(): DialogFragmentFactory = DialogFragmentFactory()

    @Provides
    fun getDialogBundleFactory(): DialogStringBundleFactory = DialogStringBundleFactory()

    @Provides
    @ActivityScope
    fun getDialogFragmentNavigator(
        fm : FragmentManager,
        dialogFactory: DialogFragmentFactory,
        bundleFactory: DialogStringBundleFactory
    ): DialogFragmentNavigator =
        DialogFragmentNavigator(
            fm,
            dialogFactory,
            bundleFactory
        )

    @Provides
    fun getScreenNavigator(fm: FragmentManager, activity: AppCompatActivity) =
        ScreenNavigator(fm, activity)

    @Provides
    @ActivityScope
    fun getSoftKeyDismisser(application : Application, activity: AppCompatActivity) =
        KeyboardDismisser(application, activity)
}