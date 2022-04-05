package vanrrtech.app.kompasgithubapp.app.DependancyInjenction.Activity

import dagger.Subcomponent
import vanrrtech.app.prodiaappsample.features.login.LoginFragment
import vanrrtech.app.prodiaappsample.features.weather_list.view.WeatherListFragment

@FragmentScope
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(fragment: LoginFragment)
    fun inject(fragment: WeatherListFragment)
}