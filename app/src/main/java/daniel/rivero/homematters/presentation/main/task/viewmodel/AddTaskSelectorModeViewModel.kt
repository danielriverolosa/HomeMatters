package daniel.rivero.homematters.presentation.main.task.viewmodel

import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.presentation.base.BaseViewModel
import daniel.rivero.homematters.presentation.main.task.event.AddTaskSelectorModeEvent
import daniel.rivero.homematters.presentation.main.task.viewstate.AddTaskSelectorModeViewState
import javax.inject.Inject

class AddTaskSelectorModeViewModel @Inject constructor(
    app: AndroidApplication
): BaseViewModel<AddTaskSelectorModeViewState, AddTaskSelectorModeEvent>(app) {

    override fun onEvent(event: AddTaskSelectorModeEvent) {
        when(event) {
            AddTaskSelectorModeEvent.Initialize -> updateViewState(AddTaskSelectorModeViewState.InitializeView)
            AddTaskSelectorModeEvent.CreateTask -> navigator.showCreateTask()
            AddTaskSelectorModeEvent.ExistingTask -> navigator.showExistingTask()
        }
    }

}