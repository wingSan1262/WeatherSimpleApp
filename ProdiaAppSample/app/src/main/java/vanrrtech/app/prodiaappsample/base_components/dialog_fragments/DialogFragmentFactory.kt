package vanrrtech.app.prodiaappsample.base_components.dialog_fragments

class DialogFragmentFactory() {

    fun getDialogNotification(okButAction: () -> Unit,
                              negativeButAction: () -> Unit) : DialogNotification {
        val dialogFragment = DialogNotification.getInstance(okButAction, negativeButAction)
        return dialogFragment
    }
}
