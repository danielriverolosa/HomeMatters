package daniel.rivero.homematters.presentation.main.task.viewstate

import daniel.rivero.homematters.domain.Task
import daniel.rivero.homematters.presentation.base.ViewState

sealed class TaskListSelectorViewState: ViewState {
    class LoadData(val defaultList: List<Task>, val customList: List<Task>) : TaskListSelectorViewState()
    class ShowError(val message: String?): TaskListSelectorViewState()

}