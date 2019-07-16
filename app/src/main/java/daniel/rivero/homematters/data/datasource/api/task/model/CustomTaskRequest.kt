package daniel.rivero.homematters.data.datasource.api.task.model

import com.google.gson.annotations.SerializedName

class CustomTaskRequest(
    @SerializedName("name_ES") val name: String,
    val houseId: String,
    val icon: String = "ic_default_task"
)