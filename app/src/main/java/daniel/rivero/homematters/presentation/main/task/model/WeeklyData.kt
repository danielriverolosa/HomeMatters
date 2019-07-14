package daniel.rivero.homematters.presentation.main.task.model

import daniel.rivero.homematters.domain.AssignedTask

class WeeklyData(
    val expendedEffort: Int,
    val pendingEffort: Int,
    val expendedTask: List<AssignedTask>,
    val pendingTask: List<AssignedTask>
)