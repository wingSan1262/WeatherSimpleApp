package vanrrtech.app.prodiaappsample.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import vanrrtech.app.prodiaappsample.base_components.BaseFragment
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
            initUi()
        }

        return viewBinding.root
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

    fun showWeatherActivity(){ screenNavigator.loginToWeatherList() }
}