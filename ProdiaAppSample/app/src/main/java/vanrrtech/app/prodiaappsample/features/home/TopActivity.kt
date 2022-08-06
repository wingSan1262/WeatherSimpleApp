package vanrrtech.app.prodiaappsample.features.home

import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.appcompat.app.AppCompatActivity
import vanrrtech.app.prodiaappsample.base_components.BaseActivity
import vanrrtech.app.prodiaappsample.databinding.TopActivityLayoutBinding

class TopActivity : BaseActivity<TopActivityLayoutBinding, TopActivityViewModel>() {
    override fun getActivity(): AppCompatActivity {return this}
    override fun onResult(result: ActivityResult) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent.inject(this)
        initUi()
        super.onCreate(savedInstanceState)
        if(loginHandler.obtainUserCredential().userName.isNotEmpty()){
            showWeatherActivity()
            return
        }
        showLogin()
    }

    fun initUi(){
        bindThisView(this, layoutInflater, null)

    }


    fun showLogin(){
        screenNavigator.startLogin()
    }

    fun showWeatherActivity(){
        screenNavigator.startWeatherList()
    }

}