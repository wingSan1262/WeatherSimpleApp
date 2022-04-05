package vanrrtech.app.prodiaappsample.dialog_fragments.dialog_navigator

import android.os.Bundle

class DialogStringBundleFactory {

    companion object {
        val TITLE = "TITLE"
        val MESSAGE = "MESSAGE"
        val POS_BUTTON_STRING = "POS_STRING"
        val NEG_BUTTON_STRING = "NEG_STRING"
    }

    fun getTwoButtonDialogStrings(title : String,
                                  message : String,
                                  posButtonString : String,
                                  negativeButtonString : String): Bundle {
        val bundle = Bundle()
        bundle.apply {
            this.putString(TITLE, title)
            this.putString(MESSAGE, message)
            this.putString(POS_BUTTON_STRING, posButtonString)
            this.putString(NEG_BUTTON_STRING, negativeButtonString)
        }
        return bundle
    }
}