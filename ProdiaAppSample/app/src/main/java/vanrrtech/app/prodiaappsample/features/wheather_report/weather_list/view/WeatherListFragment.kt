package vanrrtech.app.prodiaappsample.features.wheather_report.weather_list.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import vanrrtech.app.prodiaappsample.R
import vanrrtech.app.prodiaappsample.base_components.BaseFragment
import vanrrtech.app.prodiaappsample.base_components.UtilServices.location.LocationService
import vanrrtech.app.prodiaappsample.base_components.entities.ResourceState
import vanrrtech.app.prodiaappsample.base_components.extensions.findNullableNavController
import vanrrtech.app.prodiaappsample.base_components.extensions.navigateSafe
import vanrrtech.app.prodiaappsample.base_components.extensions.openAppSetting
import vanrrtech.app.prodiaappsample.data.RepositoryInteractor.GetMyWeatherData.GetMyWeatherInteractor
import vanrrtech.app.prodiaappsample.databinding.ActivityWeatherListBinding
import vanrrtech.app.prodiaappsample.domain.data_model.weather_data.MyWeatherParam
import vanrrtech.app.prodiaappsample.domain.data_model.weather_data.WeatherData
import vanrrtech.app.prodiaappsample.features.wheather_report.weather_list.view_model.WeatherListVM
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
        viewModel.fetchCoordinate()
        return viewBinding.root
    }

    override fun onResult(result: ActivityResult) {
        // TODO still handle permission only
        if (result.resultCode == Activity.RESULT_OK) {
            viewModel.fetchCoordinate()
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
    }

    fun initUi(){
        snackBarHandler.showSnackBar("Obtaining your region please wait a moment, it can take for a while")

        viewBinding.swipeRefresh.isRefreshing = true
        viewBinding.rvWeatherList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = weatherAdapter
        }

        viewBinding.swipeRefresh.setOnRefreshListener {
            viewModel.fetchCoordinate()
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
        findNullableNavController()?.navigateSafe(R.id.weatherList_loginFragment)
    }

    fun updateListAndCurrentWeather(data: WeatherData){
        viewBinding.error.visibility = View.GONE
        viewBinding.swipeRefresh.isRefreshing = false
        viewBinding.tvLatitude.text = data.lat.toString()
        viewBinding.tvLongitude.text = data.lon.toString()
        viewBinding.tvTimezone.text = data.timezone.toString()
        viewBinding.tvPressure.text = data.currentData.pressure.toString()
        viewBinding.tvHumidity.text = data.currentData.humidity.toString()
        viewBinding.windSpeed.text = data.currentData.windSpeed.toString()
        data.daily.run{
            lifecycleScope.launch {
                forEach {
                    weatherAdapter.onAddItem(it)
                    viewBinding.rvWeatherList.smoothScrollToPosition(0)
                    delay(500)
                }
            }
        }
    }

    fun initObserver(){
        getViewModel(WeatherListVM::class.java)
        viewModel.weatherLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ResourceState.Success -> updateListAndCurrentWeather(it.body)
                is ResourceState.Failure -> {
                    viewModel.fetchOfflineWeatherData()
                    viewBinding.swipeRefresh.isRefreshing = false
                    snackBarHandler.showSnackBar("Oops an error occurede, please check your internet connection")
                }
                else -> {}
            }
        }

        viewModel.offlineWeatherLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is ResourceState.Success -> updateListAndCurrentWeather(it.body)
                else -> {} // show error when network call fail
            }
        })

        viewModel.gpscoordinateLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is ResourceState.Success -> {
                    MyWeatherParam(
                        it.body.lat.toString(),
                        it.body.lon.toString(),
                        GetMyWeatherInteractor.TEMP_EXCLUDE_PARAM,
                        GetMyWeatherInteractor.API_KEY
                    ).run {
                        viewModel.fetchServerWeatherData(this)
                        viewModel.currentWeatherParam = this
                    }
                }
                else -> {} // show error when network call fail
            }
        })
    }


}