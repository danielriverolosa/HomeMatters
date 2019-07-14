package daniel.rivero.homematters.domain

import java.io.Serializable
import java.util.*

class AssignedTask(
    id: String,
    name: String,
    image: String,
    val effort: Int,
    val date: Date,
    val isDone: Boolean
): Task(id, name, image), Serializable