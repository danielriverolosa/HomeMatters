package daniel.rivero.homematters.data.datasource.api.user.model

class UserRequest(
    val id: String,
    val name: String,
    val email: String,
    val houseId: String?,
    val effort: Int?
)