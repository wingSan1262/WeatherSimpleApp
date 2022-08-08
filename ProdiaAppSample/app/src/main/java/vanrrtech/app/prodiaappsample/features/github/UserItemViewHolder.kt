package vanrrtech.app.prodiaappsample.features.github

import vanrrtech.app.prodiaappsample.base_components.UtilServices.LoginHandler.LoginHandlerService
import vanrrtech.app.prodiaappsample.base_components.base_classes.BaseViewHolder
import vanrrtech.app.prodiaappsample.base_components.base_interface.BaseModel
import vanrrtech.app.prodiaappsample.base_components.extensions.loadImage
import vanrrtech.app.prodiaappsample.databinding.SearchUserItemBinding
import vanrrtech.app.prodiaappsample.domain.data_model.github.response.GithubUserItemResponse

class UserItemViewHolder(
    val vhBinding : SearchUserItemBinding,
    val randomHandler : LoginHandlerService
    ) : BaseViewHolder<BaseModel>(vhBinding) {
    override fun bindData(model: BaseModel) {
        model as GithubUserItemResponse
        with(vhBinding){
            profilePict.loadImage(model.avatarUrl)
            nameTv.text = model.login
            loginTv.text = "@ ${model.login}"
            tvDetailDesc.text = randomHandler.randomBioDescGenerator()
            locationTv.text = randomHandler.randomLocationDescGenerator()
        }
    }
}