package vanrrtech.app.prodiaappsample.DependancyInjenction.Activity.ViewBinderFactory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import vanrrtech.app.prodiaappsample.features.home.TopActivity
import vanrrtech.app.prodiaappsample.databinding.ActivityWeatherListBinding
import vanrrtech.app.prodiaappsample.databinding.LoginActivityBinding
import vanrrtech.app.prodiaappsample.databinding.TopActivityLayoutBinding
import vanrrtech.app.prodiaappsample.features.login.LoginFragment
import vanrrtech.app.prodiaappsample.features.weather_list.view.WeatherListFragment

class ViewBinderFactory() {

    fun <S> bindViewFragment(modelClass : Class<S>,
                             fragment : Fragment,
                             inflater: LayoutInflater,
                             container : ViewGroup) {
        when (modelClass){
            LoginFragment::class.java -> {
                (fragment as LoginFragment).viewBinding = LoginActivityBinding.inflate(inflater,
                    container,
                    false
                    )
            }
            WeatherListFragment::class.java -> {
                (fragment as WeatherListFragment).viewBinding = ActivityWeatherListBinding.inflate(inflater,
                    container,
                    false
                )
            }

            else -> { throw Exception("no vb class found")}
        }
    }

    fun <S> bindViewActivity(modelClass : Class<S>,
                             activity : AppCompatActivity,
                             inflater: LayoutInflater) {
        when (modelClass){
            TopActivity::class.java -> {
                (activity as TopActivity).viewBinding = TopActivityLayoutBinding.inflate(inflater,
                    null,
                    false
                )
            }

            else -> { throw Exception("no vb class found")}
        }
    }
}