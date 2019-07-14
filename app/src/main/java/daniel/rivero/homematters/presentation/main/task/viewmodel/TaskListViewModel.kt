package daniel.rivero.homematters.presentation.main.task.viewmodel

import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.presentation.base.BaseViewModel
import daniel.rivero.homematters.presentation.main.task.event.TaskListEvent
import daniel.rivero.homematters.presentation.main.task.viewstate.TaskListViewState
import javax.inject.Inject

class TaskListViewModel @Inject constructor(
    app: AndroidApplication
): BaseViewModel<TaskListViewState, TaskListEvent>(app) {

    override fun onEvent(event: TaskListEvent) {
        when(event) {
            is TaskListEvent.Initialize -> updateViewState(TaskListViewState.LoadData(event.taskList))
            is TaskListEvent.OnClickTask -> navigator.showScheduleTask(event.task)
        }
    }

}