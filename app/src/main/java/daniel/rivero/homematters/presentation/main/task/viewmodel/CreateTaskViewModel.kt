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
import daniel.rivero.homematters.presentation.main.task.event.CreateTaskEvent
import daniel.rivero.homematters.presentation.main.task.event.TaskDetailEvent
import daniel.rivero.homematters.presentation.main.task.viewstate.CreateTaskViewState
import java.util.*
import javax.inject.Inject

class CreateTaskViewModel @Inject constructor(
    app: AndroidApplication,
    private val preferenceService: PreferenceService,
    private val getUserListByHomeUseCase: GetUserListByHomeUseCase,
    private val assignTaskUseCase: AssignTaskUseCase
) : BaseViewModel<CreateTaskViewState, CreateTaskEvent>(app) {

    override fun onEvent(event: CreateTaskEvent) {
        when (event) {
            CreateTaskEvent.Initialize -> loadInitialData()
            is CreateTaskEvent.EffortChange -> onEffortChange(event.value)
            is CreateTaskEvent.Continue -> validateForm(
                event.name,
                event.assignedUser,
                event.date,
                event.taskEffort,
                event.isDone
            )
        }
    }

    private fun loadInitialData() {
        preferenceService.getDefaultHome()?.let {
            getUserListByHomeUseCase(it.id)
                .subscribe({ users -> updateViewState(CreateTaskViewState.LoadInitialData(users)) }) {
                    preferenceService.getUser()
                        ?.let { user -> updateViewState(CreateTaskViewState.LoadInitialData(listOf(user))) }
                }
        }
    }

    private fun onEffortChange(value: Int) {
        val effort = EffortResolver.getTaskEffort(value)
        updateViewState(CreateTaskViewState.OnEffortChange(effort))
    }

    private fun validateForm(name: String?, assignedUser: User?, date: Date?, taskEffort: TaskEffort?, isDone: Boolean) {
        when {
            name == null -> updateViewState(CreateTaskViewState.NameHasRequired)
            assignedUser == null -> updateViewState(CreateTaskViewState.UserHasRequired)
            date == null -> updateViewState(CreateTaskViewState.DateHasRequired)
            taskEffort == null -> updateViewState(CreateTaskViewState.EffortHasRequired)
            else -> manageTask(name, assignedUser, date, taskEffort, isDone)
        }
    }

    @SuppressLint("CheckResult")
    private fun manageTask(
        name: String,
        assignedUser: User,
        date: Date,
        taskEffort: TaskEffort,
        isDone: Boolean
    ) {
        updateViewState(CreateTaskViewState.ShowLoadingWhileSaving)
            assignTaskUseCase(
                AssignTaskDto(name, taskEffort, date, assignedUser.id, assignedUser.homeId, isDone)
            ).subscribe(::onSuccess, ::onError)

    }

    private fun onSuccess() {
        updateViewState(CreateTaskViewState.Close)
    }

    private fun onError(t: Throwable) {
        updateViewState(CreateTaskViewState.ShowError(t.message))
    }

}