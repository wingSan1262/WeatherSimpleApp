package vanrrtech.app.prodiaappsample.features.github.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import vanrrtech.app.prodiaappsample.base_components.base_classes.BaseFragment
import vanrrtech.app.prodiaappsample.databinding.TopFragmentLayoutBinding

class TopFragment : BaseFragment<TopFragmentLayoutBinding, TopFragmentVm>() {
    override fun onResult(result: ActivityResult) {
        TODO("Not yet implemented")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        fragmentComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        container?.let {
            bindThisView(this, layoutInflater, it)
        }
        return viewBinding.root
    }
}