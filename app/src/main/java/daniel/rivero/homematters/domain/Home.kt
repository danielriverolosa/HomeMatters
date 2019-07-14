package daniel.rivero.homematters.domain

import java.io.Serializable

class Home(
    val id: String,
    val name: String,
    val admin: User
): Serializable