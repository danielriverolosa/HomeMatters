package daniel.rivero.homematters.domain.interactor.task.assign

import daniel.rivero.homematters.domain.AssignedTask
import daniel.rivero.homematters.domain.Home

class AssignTaskDto(
    val assignedTask: AssignedTask,
    val home: Home
)