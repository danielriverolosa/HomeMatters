package daniel.rivero.homematters.data.datasource.api.signup.model

class SignUpRequest(
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)