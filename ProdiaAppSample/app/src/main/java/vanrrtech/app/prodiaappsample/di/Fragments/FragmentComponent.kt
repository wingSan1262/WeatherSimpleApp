package vanrrtech.app.kompasgithubapp.app.DependancyInjenction.Activity

import dagger.Subcomponent
import vanrrtech.app.prodiaappsample.features.github.SearchFragment
import vanrrtech.app.prodiaappsample.features.github.UserDetailFragment
import vanrrtech.app.prodiaappsample.features.github.home.TopFragment
import vanrrtech.app.prodiaappsample.features.wheather_report.login.LoginFragment
import vanrrtech.app.prodiaappsample.features.wheather_report.weather_list.view.WeatherListFragment

@FragmentScope
@Subcomponent(modules = [
    FragmentModule::class
])
interface FragmentComponent {

    fun inject(fragment: LoginFragment)
    fun inject(fragment: WeatherListFragment)
    fun inject(fragment: TopFragment)
    fun inject(fragment: SearchFragment)
    fun inject(fragment: UserDetailFragment)

}