package daniel.rivero.homematters.domain

import java.io.Serializable
import java.util.*

class AssignedTask(
    id: String,
    name: String,
    image: String,
    val effort: TaskEffort,
    val date: Date,
    val isDone: Boolean,
    val assignedUser: User? = null
): Task(id, name, image), Serializable