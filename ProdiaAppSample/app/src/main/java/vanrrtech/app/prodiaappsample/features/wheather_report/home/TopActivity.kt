package vanrrtech.app.prodiaappsample.features.wheather_report.home

import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.appcompat.app.AppCompatActivity
import vanrrtech.app.prodiaappsample.R
import vanrrtech.app.prodiaappsample.base_components.BaseActivity
import vanrrtech.app.prodiaappsample.base_components.extensions.findNullableNavController
import vanrrtech.app.prodiaappsample.base_components.extensions.navigateSafe
import vanrrtech.app.prodiaappsample.databinding.TopActivityLayoutBinding

class TopActivity : BaseActivity<TopActivityLayoutBinding, TopActivityViewModel>() {
    override fun getActivity(): AppCompatActivity {return this}
    override fun onResult(result: ActivityResult) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent.inject(this)
        initUi()
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().apply {
            add(viewBinding.fragmentContainer.id, TopFragment())
            commit()
        }
        // do nothing
    }

    fun initUi(){
        bindThisView(this, layoutInflater, null)
    }


}