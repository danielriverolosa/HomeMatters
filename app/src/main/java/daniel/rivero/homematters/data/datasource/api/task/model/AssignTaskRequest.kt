package daniel.rivero.homematters.data.datasource.api.task.model

import com.google.gson.annotations.SerializedName

class AssignTaskRequest(
    @SerializedName("name_ES") val name: String,
    val icon: String,
    val effort: Int,
    val date: Long,
    val userId: String,
    val houseId: String?,
    val isDone: Boolean = false,
    val id: String? = null
)