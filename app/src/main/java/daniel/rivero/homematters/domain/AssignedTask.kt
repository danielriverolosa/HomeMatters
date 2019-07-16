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
    val userId: String,
    val homeId: String?
): Task(id, name, image), Serializable