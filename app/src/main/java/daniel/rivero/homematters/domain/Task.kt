package daniel.rivero.homematters.domain

import java.io.Serializable

open class Task(
    val id: String,
    val name: String,
    val image: String
): Serializable