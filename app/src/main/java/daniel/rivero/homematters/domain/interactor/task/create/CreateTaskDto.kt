package daniel.rivero.homematters.domain.interactor.task.create

import daniel.rivero.homematters.domain.TaskEffort
import daniel.rivero.homematters.domain.User
import java.util.*

class CreateTaskDto(
    val name: String,
    val date: Date,
    val effort: TaskEffort,
    val assignedUser: User
)