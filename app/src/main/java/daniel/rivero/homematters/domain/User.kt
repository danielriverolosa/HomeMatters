package daniel.rivero.homematters.domain

import java.io.Serializable

class User(
    val id: String,
    val name: String,
    val email: String,
    val home: Home? = null,
    val weeklyEffort: Int = 0
): Serializable