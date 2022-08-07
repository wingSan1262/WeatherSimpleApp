package vanrrtech.app.prodiaappsample.features.wheather_report.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import vanrrtech.app.prodiaappsample.R
import vanrrtech.app.prodiaappsample.base_components.BaseFragment
import vanrrtech.app.prodiaappsample.base_components.extensions.findNullableNavController
import vanrrtech.app.prodiaappsample.base_components.extensions.navigateSafe
import vanrrtech.app.prodiaappsample.databinding.TopActivityLayoutBinding
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