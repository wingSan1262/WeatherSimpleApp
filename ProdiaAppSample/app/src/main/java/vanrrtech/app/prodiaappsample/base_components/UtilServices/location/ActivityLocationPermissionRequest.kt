package vanrrtech.app.prodiaappsample.base_components.UtilServices.location

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import vanrrtech.app.prodiaappsample.base_components.base_classes.BaseActivity
import vanrrtech.app.prodiaappsample.base_components.base_classes.BaseFragment

class ActivityLocationPermissionRequest : AppCompatActivity() {

    companion object {
        val REQUEST_CODE = 99
        fun requestLocation(activity: BaseActivity<*, *>){
            val intent = Intent(activity, ActivityLocationPermissionRequest::class.java)
            activity.resultLauncher.launch(intent)
        }

        fun requestLocation(fragment : BaseFragment<*, *>){
            val intent = Intent(fragment.requireContext(), ActivityLocationPermissionRequest::class.java)
            fragment.resultLauncher.launch(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermission()
    }

    fun requestPermission(){
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
                ),
            REQUEST_CODE);
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        setResult(RESULT_CANCELED)

        if(grantResults.size == 0) { finish(); return; }
        for( i in grantResults.indices) {
            if(grantResults[i] != PackageManager.PERMISSION_GRANTED){ finish(); return; }
        }

        setResult(RESULT_OK)
        finish()
    }
}