package daniel.rivero.homematters.presentation.main.task.viewmodel

import daniel.rivero.homematters.domain.AssignedTask
import daniel.rivero.homematters.domain.Task
import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.presentation.base.BaseViewModel
import daniel.rivero.homematters.presentation.common.utils.EffortResolver
import daniel.rivero.homematters.presentation.main.task.event.TaskDetailEvent
import daniel.rivero.homematters.presentation.main.task.viewstate.TaskDetailViewState
import javax.inject.Inject

class TaskDetailViewModel @Inject constructor(
    app: AndroidApplication
): BaseViewModel<TaskDetailViewState, TaskDetailEvent>(app) {

    override fun onEvent(event: TaskDetailEvent) {
        when(event) {
            is TaskDetailEvent.Initialize -> loadInitialData(event.task)
            is TaskDetailEvent.EffortChange -> updateViewState(TaskDetailViewState.OnEffortChange(EffortResolver.getTaskEffort(event.value)))
        }
    }

    private fun loadInitialData(task: Task) {
        when(task) {
            is AssignedTask -> updateViewState(TaskDetailViewState.LoadAssignedTaskData(task, emptyList()))
            else -> updateViewState(TaskDetailViewState.LoadTaskData(task, emptyList()))
        }
    }

}