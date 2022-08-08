package vanrrtech.app.prodiaappsample.base_components.UtilServices.LoginHandler

import vanrrtech.app.prodiaappsample.base_components.UtilServices.shared_preference.SharedPreferenceService

class LoginHandlerService(val sharedPreferenceService: SharedPreferenceService) {

    companion object {
        val USER_NAME_SP_KEY = "user-name-sp-storage"
        val USER_PASSWORD_SP_KEY = "user-pass-sp-storage"
        val USER_IMG_SP_KEY = "user-img-sp-storage"
    }

    val mImageList = ArrayList<String>()

    val mAddressList = ArrayList<String>()

    init {
        mImageList.add("Director of Netshare Company")
        mImageList.add("Director of Mihoyo Company")
        mImageList.add("Simple minded android developer, love to sleep")
        mImageList.add("I'm ajaib eng, sleep while i code, code while i sleep")
        mImageList.add("nah just see my repo broo . . .")

        mAddressList.add("Taipei, Taiwan")
        mAddressList.add("Depok, Jawa Barat")
        mAddressList.add("Oslo, Poland")
        mAddressList.add("Kyoto, Japan")
        mAddressList.add("Hirooka, Japan")
    }

    fun randomBioDescGenerator() : String {
        val index = (Math.random() * mImageList.size).toInt()
        return mImageList.get(index)
    }

    fun randomLocationDescGenerator() : String {
        val index = (Math.random() * mAddressList.size).toInt()
        return mAddressList.get(index)
    }

    fun randomImageGenerator() : String {
        val index = (Math.random() * mImageList.size).toInt()
        return mImageList.get(index)
    }

    fun login (userName : String, userPassword: String): Boolean {
        if(!validateUserCredentialInfo(userName, userPassword)){ return false}
        sharedPreferenceService.insertString(USER_NAME_SP_KEY, userName)
        sharedPreferenceService.insertString(USER_PASSWORD_SP_KEY, userPassword)
        sharedPreferenceService.insertString(USER_IMG_SP_KEY, randomImageGenerator())
        return true
    }

    fun logout(){
        sharedPreferenceService.delete(USER_NAME_SP_KEY)
        sharedPreferenceService.delete(USER_PASSWORD_SP_KEY)
        sharedPreferenceService.delete(USER_IMG_SP_KEY)
    }

    fun obtainUserCredential(): LoginCredential {
        return LoginCredential(
            sharedPreferenceService.getString(USER_NAME_SP_KEY),
            sharedPreferenceService.getString(USER_PASSWORD_SP_KEY),
            sharedPreferenceService.getString(USER_IMG_SP_KEY)
        )
    }

    private fun validateUserCredentialInfo(userName : String, userPassword: String): Boolean {
        return (userName.isNotEmpty() && userPassword.isNotEmpty())
    }

    data class LoginCredential(val userName : String,
                               val userPassword : String,
                               val userImg : String,
    )
}