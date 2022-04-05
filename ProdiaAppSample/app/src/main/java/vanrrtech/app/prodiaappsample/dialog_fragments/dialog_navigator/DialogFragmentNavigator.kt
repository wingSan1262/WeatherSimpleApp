package vanrrtech.app.prodiaappsample.dialog_fragments.dialog_navigator

import androidx.fragment.app.FragmentManager
import vanrrtech.app.prodiaappsample.dialog_fragments.DialogFragmentFactory

class DialogFragmentNavigator(val fm : FragmentManager,
                              val dialogFactory : DialogFragmentFactory,
                              val dialogStringBundleFactory: DialogStringBundleFactory) {

    fun openTwoButtonDialog (
                             title : String,
                             message : String,
                             posButtonString : String,
                             negativeButtonString : String,
                             okButAction: () -> Unit,
                             negativeButAction: () -> Unit){
        val bundle = dialogStringBundleFactory.getTwoButtonDialogStrings(
            title, message,
            posButtonString,
            negativeButtonString
        )
        val dialog = dialogFactory.getDialogNotification(okButAction, negativeButAction)
        dialog.arguments = bundle
        dialog.show(fm, "notification dialog")
    }
}