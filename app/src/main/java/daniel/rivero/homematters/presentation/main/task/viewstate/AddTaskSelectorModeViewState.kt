package daniel.rivero.homematters.presentation.main.task.viewstate

import daniel.rivero.homematters.presentation.base.ViewState

sealed class AddTaskSelectorModeViewState: ViewState {
    object InitializeView: AddTaskSelectorModeViewState()
}