package vanrrtech.app.prodiaappsample.domain.data_model.github

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName =  "user_list_table")
data class GithubUserItemResponse(
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    var id : Int,

    @SerializedName("login")
    @ColumnInfo(name = "login")
    var login : String,


    @SerializedName("avatar_url")
    @ColumnInfo(name = "avatar_url")
    var avatarUrl : String,

    @SerializedName("url")
    @ColumnInfo(name = "url")
    var url : String,

    @SerializedName("repos_url")
    @ColumnInfo(name = "repos_url")
    var repos_url : String,
) : Serializable