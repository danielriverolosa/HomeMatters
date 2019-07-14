package daniel.rivero.homematters.presentation.main.task.event

import daniel.rivero.homematters.presentation.base.Event

sealed class TaskManagerEvent: Event {
    object Initialize: TaskManagerEvent()
    object AddTask : TaskManagerEvent()
}