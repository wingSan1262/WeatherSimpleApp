package vanrrtech.app.prodiaappsample.base_components.extensions

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.Navigator
import vanrrtech.app.prodiaappsample.base_components.BaseActivity
import vanrrtech.app.prodiaappsample.base_components.BaseFragment

fun BaseActivity<*,*>.findNullableNavController(): NavController? {
    var navController : NavController? = null
    try {
        navController = (Navigation.findNavController(this.viewBinding.root) ?: null)
    } catch (e : Throwable){navController = null; Log.d("app log", "nav controller heckled up")}
     return navController
}

fun BaseActivity<*,*>.openAppSetting(){
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    val uri: Uri = Uri.fromParts("package", packageName, null)
    intent.data = uri
    startActivity(intent)
}

fun BaseFragment<*,*>.findNullableNavController(): NavController? {
    var navController : NavController? = null
    try {
        navController = (Navigation.findNavController(this.viewBinding.root) ?: null)
    } catch (e : Throwable){navController = null; Log.d("app log", "nav controller heckled up")}
    return navController
}

fun BaseFragment<*,*>.openAppSetting(){
    hostActivity.openAppSetting()
}

fun NavController.navigateSafe(
    @IdRes resId: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null,
    navExtras: Navigator.Extras? = null
) {
    val action = currentDestination?.getAction(resId) ?: graph.getAction(resId)
    if (action != null && currentDestination?.id != action.destinationId) {
        navigate(resId, args, navOptions, navExtras)
    }
}
