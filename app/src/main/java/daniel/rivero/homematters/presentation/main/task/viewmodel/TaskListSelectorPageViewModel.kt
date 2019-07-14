package daniel.rivero.homematters.presentation.main.task.viewmodel

import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.presentation.base.BaseViewModel
import daniel.rivero.homematters.presentation.main.task.event.TaskListSelectorPageEvent
import daniel.rivero.homematters.presentation.main.task.viewstate.TaskListSelectorPageViewState
import javax.inject.Inject

class TaskListSelectorPageViewModel @Inject constructor(
    app: AndroidApplication
): BaseViewModel<TaskListSelectorPageViewState, TaskListSelectorPageEvent>(app) {

    override fun onEvent(event: TaskListSelectorPageEvent) {
        when(event) {
            is TaskListSelectorPageEvent.Initialize -> updateViewState(TaskListSelectorPageViewState.LoadData(event.taskList))
            is TaskListSelectorPageEvent.OnClickTask -> navigator.showScheduleTask(event.task)
        }
    }

}