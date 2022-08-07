package vanrrtech.app.kompasgithubapp.app.DependancyInjenction.Activity

import dagger.Subcomponent
import vanrrtech.app.prodiaappsample.DependancyInjenction.App.UseCasesModules
import vanrrtech.app.prodiaappsample.features.wheather_report.home.TopFragment
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
}