package vanrrtech.app.prodiaappsample.dialog_fragments

import vanrrtech.app.prodiaappsample.dialog_fragments.dialog_navigator.DialogStringBundleFactory

class DialogFragmentFactory() {

    fun getDialogNotification(okButAction: () -> Unit,
                              negativeButAction: () -> Unit) : DialogNotification {
        val dialogFragment = DialogNotification.getInstance(okButAction, negativeButAction)
        return dialogFragment
    }
}
