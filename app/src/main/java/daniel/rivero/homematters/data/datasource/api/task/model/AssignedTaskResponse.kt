package daniel.rivero.homematters.data.datasource.api.task.model

import com.google.gson.annotations.SerializedName

class AssignedTaskResponse(
    val id: String,
    @SerializedName("name_ES") val name: String,
    val icon: String,
    val effort: Int,
    val date: String,
    val isDone: Boolean
)