package daniel.rivero.homematters.presentation.main.task.event

import daniel.rivero.homematters.presentation.base.Event

sealed class TaskListSelectorEvent: Event {
    object Initialize: TaskListSelectorEvent()
}