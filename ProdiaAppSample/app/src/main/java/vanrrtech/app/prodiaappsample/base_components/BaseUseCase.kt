package vanrrtech.app.prodiaappsample.base_components

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import vanrrtech.app.prodiaappsample.base_components.abstracts.UseCase
import vanrrtech.app.prodiaappsample.base_components.entities.ResourceState
import kotlin.coroutines.CoroutineContext

open class BaseUseCase<PARAM, RESULT> : CoroutineScope, UseCase() {

    private val _currentData = MutableLiveData<ResourceState<RESULT>>()
    val currentData : LiveData<ResourceState<RESULT>> = _currentData

    protected var job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    fun execute(runApi : suspend () -> RESULT?){
        launch(coroutineContext){
            val res = try {
                val data = runApi()
                if(data != null)
                    ResourceState.Success(data as RESULT) else
                    ResourceState.Failure(Throwable("There is something wrong in the network"))
            } catch (e : Throwable){
                ResourceState.Failure(
                    Throwable("There is something wrong in the network"))
            }
            withContext(Dispatchers.Main){
                _currentData.value = res
            }
        }
    }

    open fun setup(parameter: PARAM){ checkJob() }
    fun cancel() { job.cancel() }
    fun isCancelled(): Boolean { return job.isCancelled }
    fun checkJob(){
        if(job.isCancelled)
            job = Job() }
}