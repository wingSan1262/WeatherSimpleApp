package vanrrtech.app.prodiaappsample.features.weather_list.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import vanrrtech.app.prodiaappsample.base_components.BaseFragment
import vanrrtech.app.prodiaappsample.base_components.UtilServices.location.LocationService
import vanrrtech.app.prodiaappsample.databinding.ActivityWeatherListBinding
import vanrrtech.app.prodiaappsample.features.weather_list.view_model.WeatherListVM
import javax.inject.Inject

class WeatherListFragment : BaseFragment<ActivityWeatherListBinding, WeatherListVM>() {

    @Inject lateinit var locationService: LocationService
    @Inject lateinit var weatherAdapter : WeatherListAdapter

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
        initUi()
        initObserver()
        if(!locationService.isPermissionGranted()){
            requestPermission()
        }
        return viewBinding.root
    }

    override fun onResult(result: ActivityResult) {
        // TODO still handle permission only
        if (result.resultCode == Activity.RESULT_OK) {
            finish()
            screenNavigator.startWeatherList()
        } else {
            viewBinding.swipeRefresh.isRefreshing = false
            viewBinding.tvNoDataError.text = "You deny the permission, please restart app / approve it manually by click here so we can fetch the weather data"
            viewBinding.tvNoDataError.setOnClickListener {
                openAppSetting()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.destroy()
    }

    fun initUi(){

        snackBarHandler.showSnackBar("Obtaining your region please wait a moment, it can take for a while")

        viewBinding.swipeRefresh.isRefreshing = true
        viewBinding.rvWeatherList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = weatherAdapter
        }

        viewBinding.swipeRefresh.setOnRefreshListener {
            viewModel.fetchData()
            weatherAdapter.reset()
        }

        val userData = loginHandler.obtainUserCredential()
        viewBinding.tvUserName.text = userData.userName
        imageLoader.loadImage(userData.userImg, viewBinding.imgUser)
        viewBinding.tvLogout.setOnClickListener {
            dialogFragmentNavigator.openTwoButtonDialog(
                "Do you want to log out",
                "All your credential will be all deleted",
                "Ok",
                "Cancel",
                { loggingOut() },
                { /** do nothing**/ }
            )
        }
    }

    fun loggingOut() {
        loginHandler.logout()
        screenNavigator.weatherListToLogin()
    }

    fun initObserver(){
        getViewModel(WeatherListVM::class.java)

        viewModel.fetchData()
        viewModel.currentData.observe(viewLifecycleOwner, Observer {
            viewBinding.error.visibility = View.GONE
            viewBinding.swipeRefresh.isRefreshing = false
            viewBinding.tvLatitude.text = it?.lat.toString()
            viewBinding.tvLongitude.text = it?.lon.toString()
            viewBinding.tvTimezone.text = it?.timezone.toString()
            viewBinding.tvPressure.text = it?.currentData?.pressure.toString()
            viewBinding.tvHumidity.text = it?.currentData?.humidity.toString()
            viewBinding.windSpeed.text = it?.currentData?.windSpeed.toString()
        })

        viewModel.dailyWeather.observe(viewLifecycleOwner, Observer {
            if(it == null){return@Observer}
            weatherAdapter.onAddItem(it)
            viewBinding.rvWeatherList.smoothScrollToPosition(0)
        })

        viewModel.isError.observe(viewLifecycleOwner, Observer {

            if (it == true){
                viewBinding.swipeRefresh.isRefreshing = false
                snackBarHandler.showSnackBar("Oops an error occurede, please check your internet connection")
            }
        })
    }


}