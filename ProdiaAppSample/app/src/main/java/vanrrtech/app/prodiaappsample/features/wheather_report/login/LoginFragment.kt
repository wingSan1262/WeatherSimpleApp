package vanrrtech.app.prodiaappsample.features.wheather_report.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import vanrrtech.app.prodiaappsample.R
import vanrrtech.app.prodiaappsample.base_components.BaseFragment
import vanrrtech.app.prodiaappsample.base_components.extensions.findNullableNavController
import vanrrtech.app.prodiaappsample.base_components.extensions.navigateSafe
import vanrrtech.app.prodiaappsample.databinding.LoginActivityBinding

class LoginFragment : BaseFragment<LoginActivityBinding, LoginVM>() {

    override fun onResult(result: ActivityResult) {TODO("empty on result no implemented")}
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

    override fun onResume() {
        super.onResume()
        if(!checkLoginStatus())
            initUi()
    }

    fun initUi(){

        dialogFragmentNavigator.openTwoButtonDialog(
            "This is dummy login",
            "you can insert any value to the login field as long is not empty",
            "Ok",
            "",
            {},
            {},
        )

        viewBinding.btnSignIn.setOnClickListener {
            if (loginHandler
                    .login(
                        viewBinding.fieldUsername.text.toString(),
                        viewBinding.fieldPassword.text.toString())
            ) {
                dismissKey()
                showWeatherActivity()
            } else {
                snackBarHandler.showSnackBar("you enter invalid string format")
            }
        }
    }

    fun checkLoginStatus(): Boolean {
        if(loginHandler.obtainUserCredential().userName.isNotEmpty()){
            findNullableNavController()?.navigateSafe(
                R.id.loginFragment_toWeatherList
            )
            return true
        } else {
            findNullableNavController()?.navigateSafe(
                R.id.weatherList_loginFragment
            )
            return false
        }
    }

    fun showWeatherActivity(){
        findNullableNavController()?.navigateSafe(
            R.id.loginFragment_toWeatherList
        )
    }
}