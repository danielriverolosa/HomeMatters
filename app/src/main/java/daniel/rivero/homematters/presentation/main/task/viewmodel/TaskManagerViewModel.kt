package daniel.rivero.homematters.presentation.main.task.viewmodel

import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.presentation.base.BaseViewModel
import daniel.rivero.homematters.presentation.main.task.event.TaskManagerEvent
import daniel.rivero.homematters.presentation.main.task.model.WeeklyData
import daniel.rivero.homematters.presentation.main.task.viewstate.TaskManagerViewState
import javax.inject.Inject

class TaskManagerViewModel @Inject constructor(
    app: AndroidApplication
): BaseViewModel<TaskManagerViewState, TaskManagerEvent>(app) {

    override fun onEvent(event: TaskManagerEvent) {
        when(event) {
            TaskManagerEvent.Initialize -> loadData()
            TaskManagerEvent.AddTask -> navigator.showAddTaskSelectorMode()
        }
    }

    private fun loadData() {
        updateViewState(TaskManagerViewState.LoadData(WeeklyData(25, 40, emptyList(), emptyList())))
    }

}