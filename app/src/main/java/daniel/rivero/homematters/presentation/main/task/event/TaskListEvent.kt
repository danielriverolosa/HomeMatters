package daniel.rivero.homematters.presentation.main.task.event

import daniel.rivero.homematters.domain.AssignedTask
import daniel.rivero.homematters.presentation.base.Event

sealed class TaskListEvent: Event {
    class Initialize(val taskList: List<AssignedTask>): TaskListEvent()
    class OnClickTask(val task: AssignedTask) : TaskListEvent()
}