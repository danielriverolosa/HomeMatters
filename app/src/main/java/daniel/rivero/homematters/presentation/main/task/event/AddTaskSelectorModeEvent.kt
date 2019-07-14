package daniel.rivero.homematters.presentation.main.task.event

import daniel.rivero.homematters.presentation.base.Event

sealed class AddTaskSelectorModeEvent: Event {

    object Initialize: AddTaskSelectorModeEvent()
    object CreateTask: AddTaskSelectorModeEvent()
    object ExistingTask: AddTaskSelectorModeEvent()

}