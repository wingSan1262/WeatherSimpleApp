package vanrrtech.app.prodiaappsample.features.github

import android.view.LayoutInflater
import android.view.ViewGroup
import vanrrtech.app.prodiaappsample.base_components.UtilServices.LoginHandler.LoginHandlerService
import vanrrtech.app.prodiaappsample.base_components.base_classes.BaseAdapter
import vanrrtech.app.prodiaappsample.base_components.base_classes.BaseViewHolder
import vanrrtech.app.prodiaappsample.base_components.base_interface.BaseModel
import vanrrtech.app.prodiaappsample.databinding.SearchUserItemBinding

class UserListAdapter(
    val randomHandler : LoginHandlerService,
    val itemClick : (BaseModel) -> Unit = {}
) : BaseAdapter<BaseViewHolder<BaseModel>>() {

    override fun bindVH(holder: BaseViewHolder<BaseModel>, position: Int) {
        holder.bindData(listItems[position])
        holder.itemView.setOnClickListener {
            itemClick
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BaseModel> {
        return UserItemViewHolder(
            SearchUserItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            randomHandler
        )
    }

}