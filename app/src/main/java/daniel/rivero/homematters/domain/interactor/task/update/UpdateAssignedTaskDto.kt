package daniel.rivero.homematters.domain.interactor.task.update

import daniel.rivero.homematters.domain.AssignedTask
import daniel.rivero.homematters.domain.Home

class UpdateAssignedTaskDto(
    val assignedTask: AssignedTask,
    val home: Home
)