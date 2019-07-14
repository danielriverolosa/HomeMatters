package daniel.rivero.homematters.presentation.main.task.event

import daniel.rivero.homematters.domain.Task
import daniel.rivero.homematters.presentation.base.Event

sealed class TaskListSelectorPageEvent: Event {
    class Initialize(val taskList: List<Task>): TaskListSelectorPageEvent()
    class OnClickTask(val task: Task): TaskListSelectorPageEvent()
}