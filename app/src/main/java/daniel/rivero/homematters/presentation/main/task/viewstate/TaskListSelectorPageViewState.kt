package daniel.rivero.homematters.presentation.main.task.viewstate

import daniel.rivero.homematters.domain.Task
import daniel.rivero.homematters.presentation.base.ViewState

sealed class TaskListSelectorPageViewState: ViewState {
    class LoadData(val taskList: List<Task>): TaskListSelectorPageViewState()
}