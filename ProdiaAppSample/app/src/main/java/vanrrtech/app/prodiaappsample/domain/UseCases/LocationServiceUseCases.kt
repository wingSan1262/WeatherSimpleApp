package vanrrtech.app.prodiaappsample.domain.UseCases

import kotlinx.coroutines.launch
import vanrrtech.app.prodiaappsample.base_components.BaseUseCase
import vanrrtech.app.prodiaappsample.base_components.UtilServices.location.LocationService

class LocationServiceUseCases(
    val myApi : LocationService
) : BaseUseCase<Any?, LocationService.coordinateData>() {

    override fun setup(parameter: Any?) {
        super.setup(parameter)
        launch(coroutineContext) {
            execute {
                var data : LocationService.coordinateData? = null
                myApi.getLatAndLon().let{
                    data = it
                }
                return@execute data
            }}
    }
}