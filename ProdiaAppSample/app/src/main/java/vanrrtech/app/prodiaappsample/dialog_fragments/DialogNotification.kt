package vanrrtech.app.prodiaappsample.dialog_fragments

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DialogNotification() : BaseDialogFragment() {
    lateinit var okAction : () -> Unit
    lateinit var negativeButAction : () -> Unit

    companion object {
        fun getInstance(okButAction: () -> Unit,
                        negativeButAction: () -> Unit): DialogNotification {
            val dialog = DialogNotification()
            dialog.okAction = okButAction
            dialog.negativeButAction = negativeButAction
            return dialog
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(dialogTitle)
            .setMessage(dialogMessage)
            .setPositiveButton("OK") { dialog, which ->
                dismiss()
                okAction()
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dismiss()
                negativeButAction()
            }
            .show()
    }


}