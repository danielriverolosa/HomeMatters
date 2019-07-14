package daniel.rivero.homematters.presentation.main.task.viewstate

import daniel.rivero.homematters.domain.TaskEffort
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.presentation.base.ViewState

sealed class CreateTaskViewState: ViewState {

    object Loading: CreateTaskViewState()
    class LoadInitialData(val userList: List<User>): CreateTaskViewState()
    class OnEffortChange(val effort: TaskEffort) : CreateTaskViewState()
    class ShowError(val message: String?): CreateTaskViewState()
    object Close: CreateTaskViewState()

}