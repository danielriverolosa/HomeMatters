package daniel.rivero.homematters.domain.interactor.task.assign

import daniel.rivero.homematters.domain.TaskEffort
import java.util.*

class AssignTaskDto(
    val name: String,
    val effort: TaskEffort,
    val date: Date,
    val userId: String,
    val homeId: String?,
    val isDone: Boolean = false
)