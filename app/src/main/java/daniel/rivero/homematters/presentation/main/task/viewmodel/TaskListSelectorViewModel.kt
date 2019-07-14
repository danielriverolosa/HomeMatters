package daniel.rivero.homematters.presentation.main.task.viewmodel

import android.annotation.SuppressLint
import daniel.rivero.homematters.domain.Home
import daniel.rivero.homematters.domain.Task
import daniel.rivero.homematters.domain.interactor.task.list.custom.GetCustomTaskListByHome
import daniel.rivero.homematters.domain.interactor.task.list.suggested.GetDefaultTaskListUseCase
import daniel.rivero.homematters.domain.service.PreferenceService
import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.presentation.base.BaseViewModel
import daniel.rivero.homematters.presentation.main.task.event.TaskListSelectorEvent
import daniel.rivero.homematters.presentation.main.task.viewstate.TaskListSelectorViewState
import javax.inject.Inject

class TaskListSelectorViewModel @Inject constructor(
    app: AndroidApplication,
    private val preferenceService: PreferenceService,
    private val defaultTaskListUseCase: GetDefaultTaskListUseCase,
    private val customTaskListByHome: GetCustomTaskListByHome

): BaseViewModel<TaskListSelectorViewState, TaskListSelectorEvent>(app) {
    override fun onEvent(event: TaskListSelectorEvent) {
        when(event) {
            is TaskListSelectorEvent.Initialize -> loadInitialData(preferenceService.getDefaultHome())
        }
    }

    @SuppressLint("CheckResult")
    private fun loadInitialData(home: Home?) {
        defaultTaskListUseCase()
            .subscribe({ onSuccessDefaultList(home, it) }, ::handleError)
    }

    @SuppressLint("CheckResult")
    private fun onSuccessDefaultList(home: Home?, defaultList: List<Task>) {
        customTaskListByHome(home)
            .subscribe({ notifyDataLoading(defaultList, it) }, ::handleError)
    }

    private fun notifyDataLoading(defaultList: List<Task>, customList: List<Task>) {
        updateViewState(TaskListSelectorViewState.LoadData(defaultList, customList))
    }

    private fun handleError(t: Throwable) {
        updateViewState(TaskListSelectorViewState.ShowError(t.message))
    }

}