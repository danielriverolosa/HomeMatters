package daniel.rivero.homematters.data.datasource.api.login.model

class LoginResponse(
    val id: String,
    val name: String,
    val email: String,
    val houses: List<String>
)