package daniel.rivero.homematters.presentation.main.task.viewstate

import daniel.rivero.homematters.domain.AssignedTask
import daniel.rivero.homematters.domain.Task
import daniel.rivero.homematters.domain.TaskEffort
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.presentation.base.ViewState

sealed class TaskDetailViewState: ViewState {
    class LoadTaskData(val task: Task, val userList: List<User>) : TaskDetailViewState()
    class LoadAssignedTaskData(val task: AssignedTask, val userList: List<User>) : TaskDetailViewState()
    class OnEffortChange(val effort: TaskEffort): TaskDetailViewState()
    object UserHasRequired : TaskDetailViewState()
    object DateHasRequired : TaskDetailViewState()
    object EffortHasRequired : TaskDetailViewState()
    object ShowLoadingWhileSaving: TaskDetailViewState()
    object Close: TaskDetailViewState()
    class ShowError(val message: String?): TaskDetailViewState()
}