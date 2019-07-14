package daniel.rivero.homematters.domain.interactor.task.list

import java.util.*

class AssignedTaskListToUserDto(
    val userId: String,
    val toDate: Date,
    val fromDate: Date
)