package daniel.rivero.homematters.presentation.main.task.viewmodel

import android.annotation.SuppressLint
import daniel.rivero.homematters.domain.AssignedTask
import daniel.rivero.homematters.domain.Task
import daniel.rivero.homematters.domain.TaskEffort
import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.domain.interactor.task.assign.AssignTaskDto
import daniel.rivero.homematters.domain.interactor.task.assign.AssignTaskUseCase
import daniel.rivero.homematters.domain.interactor.task.update.UpdateAssignedTaskUseCase
import daniel.rivero.homematters.domain.interactor.user.list.GetUserListByHomeUseCase
import daniel.rivero.homematters.domain.service.PreferenceService
import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.presentation.base.BaseViewModel
import daniel.rivero.homematters.presentation.common.utils.EffortResolver
import daniel.rivero.homematters.presentation.main.task.event.TaskDetailEvent
import daniel.rivero.homematters.presentation.main.task.viewstate.TaskDetailViewState
import java.util.*
import javax.inject.Inject

class TaskDetailViewModel @Inject constructor(
    app: AndroidApplication,
    private val preferenceService: PreferenceService,
    private val getUserListByHomeUseCase: GetUserListByHomeUseCase,
    private val assignTaskUseCase: AssignTaskUseCase,
    private val updateAssignedTaskUseCase: UpdateAssignedTaskUseCase
): BaseViewModel<TaskDetailViewState, TaskDetailEvent>(app) {

    override fun onEvent(event: TaskDetailEvent) {
        when(event) {
            is TaskDetailEvent.Initialize -> loadInitialData(event.task)
            is TaskDetailEvent.EffortChange -> updateViewState(TaskDetailViewState.OnEffortChange(EffortResolver.getTaskEffort(event.value)))
            is TaskDetailEvent.Continue -> validateForm(event.task, event.assignedUser, event.date, event.taskEffort, event.isDone)
        }
    }

    private fun validateForm(task: Task, assignedUser: User?, date: Date?, taskEffort: TaskEffort?, isDone: Boolean) {
        when {
            assignedUser == null -> updateViewState(TaskDetailViewState.UserHasRequired)
            date == null -> updateViewState(TaskDetailViewState.DateHasRequired)
            taskEffort == null -> updateViewState(TaskDetailViewState.EffortHasRequired)
            else -> manageTask(task, assignedUser, date, taskEffort, isDone)
        }
    }

    @SuppressLint("CheckResult")
    private fun manageTask(
        task: Task,
        assignedUser: User,
        date: Date,
        taskEffort: TaskEffort,
        isDone: Boolean
    ) {
        updateViewState(TaskDetailViewState.ShowLoadingWhileSaving)
        if (task is AssignedTask) {
            updateAssignedTaskUseCase(getAssignedTask(task, assignedUser, date, taskEffort, isDone))
                .subscribe(::onSuccess, ::onError)
        } else {
            assignTaskUseCase(AssignTaskDto(task.name, taskEffort, date, assignedUser.id, assignedUser.homeId, isDone))
                .subscribe(::onSuccess, ::onError)
        }
    }

    private fun getAssignedTask(assignedTask: Task, assignedUser: User, date: Date, taskEffort: TaskEffort, isDone: Boolean) = AssignedTask(
        assignedTask.id,
        assignedTask.name,
        assignedTask.image,
        taskEffort,
        date,
        isDone,
        assignedUser.id,
        assignedUser.homeId
    )

    private fun onSuccess() {
        updateViewState(TaskDetailViewState.Close)
    }

    private fun onError(t: Throwable) {
        updateViewState(TaskDetailViewState.ShowError(t.message))
    }

    private fun loadInitialData(task: Task) {
        preferenceService.getDefaultHome()?.let {
            getUserListByHomeUseCase(it.id)
                .subscribe({ users -> notifyDataLoaded(task, users) }) {
                    loadWithOnlyOwnUser(task)
                }
        } ?: loadWithOnlyOwnUser(task)
    }

    private fun loadWithOnlyOwnUser(task: Task) {
        preferenceService.getUser()?.let { notifyDataLoaded(task, listOf(it)) }
    }

    private fun notifyDataLoaded(task: Task, userList: List<User>) {
        when(task) {
            is AssignedTask -> updateViewState(TaskDetailViewState.LoadAssignedTaskData(task, userList))
            else -> updateViewState(TaskDetailViewState.LoadTaskData(task, userList))
        }
    }

}