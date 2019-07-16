package daniel.rivero.homematters.domain

import java.io.Serializable

class User(
    val id: String,
    val name: String,
    val email: String,
    val homeId: String? = null,
    val weeklyEffort: Int = 0
): Serializable