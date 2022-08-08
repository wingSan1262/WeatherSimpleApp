package vanrrtech.app.kompasgithubapp.app.DependancyInjenction.Activity

import dagger.Subcomponent
import vanrrtech.app.prodiaappsample.features.github.home.TopActivity

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun newFragmentComponent (fragmentModule: FragmentModule) : FragmentComponent
    fun inject(context: TopActivity)

}