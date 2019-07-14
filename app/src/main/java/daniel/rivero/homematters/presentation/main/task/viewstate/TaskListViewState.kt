package daniel.rivero.homematters.presentation.main.task.viewstate

import daniel.rivero.homematters.domain.AssignedTask
import daniel.rivero.homematters.presentation.base.ViewState

sealed class TaskListViewState: ViewState {

    class LoadData(val taskList: List<AssignedTask>): TaskListViewState()

}