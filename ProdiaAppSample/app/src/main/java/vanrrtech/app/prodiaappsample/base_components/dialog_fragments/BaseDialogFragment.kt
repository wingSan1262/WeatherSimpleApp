package vanrrtech.app.prodiaappsample.base_components.dialog_fragments

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import vanrrtech.app.kompasgithubapp.app.DependancyInjenction.Activity.ActivityComponent
import vanrrtech.app.prodiaappsample.base_components.base_classes.BaseActivity
import vanrrtech.app.prodiaappsample.base_components.dialog_fragments.dialog_navigator.DialogStringBundleFactory.Companion.MESSAGE
import vanrrtech.app.prodiaappsample.base_components.dialog_fragments.dialog_navigator.DialogStringBundleFactory.Companion.NEG_BUTTON_STRING
import vanrrtech.app.prodiaappsample.base_components.dialog_fragments.dialog_navigator.DialogStringBundleFactory.Companion.POS_BUTTON_STRING
import vanrrtech.app.prodiaappsample.base_components.dialog_fragments.dialog_navigator.DialogStringBundleFactory.Companion.TITLE

open class BaseDialogFragment : DialogFragment() {

    lateinit var dialogTitle : String
    lateinit var dialogMessage : String
    lateinit var positiveButtonString : String
    lateinit var negativeButtonString : String

    val activityComponent : ActivityComponent by lazy {
        (requireActivity() as BaseActivity<*, *>).activityComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { getDialogStringFromIntent(it) }
    }

    fun getDialogStringFromIntent(bundle: Bundle){
        dialogTitle = bundle.getString(TITLE, "").toString()
        dialogMessage = bundle.getString(MESSAGE, "").toString()
        positiveButtonString = bundle.getString(POS_BUTTON_STRING, "").toString()
        negativeButtonString = bundle.getString(NEG_BUTTON_STRING, "").toString()
    }
}