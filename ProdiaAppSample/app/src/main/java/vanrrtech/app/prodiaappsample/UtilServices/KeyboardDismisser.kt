package vanrrtech.app.prodiaappsample.UtilServices

import android.app.Activity
import android.app.AppComponentFactory
import android.app.Application
import android.content.Context
import android.location.LocationManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService


class KeyboardDismisser(val application: Application, val activity: AppCompatActivity) {

    fun dismissSoftKey(){
        val imm : InputMethodManager = application.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        activity.currentFocus.let {
            imm.hideSoftInputFromWindow(it?.windowToken, 0)
        }
    }
}