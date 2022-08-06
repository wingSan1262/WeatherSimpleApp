package vanrrtech.app.prodiaappsample.base_components.UtilServices.screen_navigator

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import vanrrtech.app.prodiaappsample.features.home.TopActivity
import vanrrtech.app.prodiaappsample.features.login.LoginFragment
import vanrrtech.app.prodiaappsample.features.weather_list.view.WeatherListFragment

class ScreenNavigator(val fm : FragmentManager, val host : AppCompatActivity) {

    val hostId by lazy { getRootId()}
    private fun getRootId(): Int {
        if(host is TopActivity){
            return host.viewBinding.fragmentContainer.id
        } else {
            throw Throwable("host view container not found" + fm + host)
        }
    }
    fun startLogin(){
        fm.beginTransaction().apply {
            this.add(hostId, LoginFragment() )
        }.commit()
    }

    fun startWeatherList(){
        fm.beginTransaction().apply {
            this.add(hostId, WeatherListFragment())
        }.commit()
    }

    fun weatherListToLogin(){
        back()
        startLogin()
    }

    fun loginToWeatherList(){
        back()
        startWeatherList()
    }

    fun back() {
        fm.popBackStack()
    }

}