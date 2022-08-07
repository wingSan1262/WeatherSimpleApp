package vanrrtech.app.prodiaappsample.DependancyInjenction.Activity.ViewBinderFactory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import vanrrtech.app.prodiaappsample.features.wheather_report.home.TopActivity
import vanrrtech.app.prodiaappsample.databinding.ActivityWeatherListBinding
import vanrrtech.app.prodiaappsample.databinding.LoginActivityBinding
import vanrrtech.app.prodiaappsample.databinding.TopActivityLayoutBinding
import vanrrtech.app.prodiaappsample.databinding.TopFragmentLayoutBinding
import vanrrtech.app.prodiaappsample.features.wheather_report.home.TopFragment
import vanrrtech.app.prodiaappsample.features.wheather_report.login.LoginFragment
import vanrrtech.app.prodiaappsample.features.wheather_report.weather_list.view.WeatherListFragment

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
            TopFragment::class.java -> {
                (fragment as TopFragment).viewBinding = TopFragmentLayoutBinding.inflate(inflater,
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