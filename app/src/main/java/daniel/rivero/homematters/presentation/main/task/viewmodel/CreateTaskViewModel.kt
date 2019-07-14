package daniel.rivero.homematters.presentation.main.task.viewmodel

import daniel.rivero.homematters.domain.User
import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.presentation.base.BaseViewModel
import daniel.rivero.homematters.presentation.common.utils.EffortResolver
import daniel.rivero.homematters.presentation.main.task.event.CreateTaskEvent
import daniel.rivero.homematters.presentation.main.task.viewstate.CreateTaskViewState
import javax.inject.Inject

class CreateTaskViewModel @Inject constructor(
    app: AndroidApplication
): BaseViewModel<CreateTaskViewState, CreateTaskEvent>(app) {

    override fun onEvent(event: CreateTaskEvent) {
        when(event) {
            CreateTaskEvent.Initialize -> loadInitialData()
            is CreateTaskEvent.EffortChange -> onEffortChange(event.value)
        }
    }

    private fun loadInitialData() {
        updateViewState(CreateTaskViewState.LoadInitialData(getUserList()))
    }

    private fun onEffortChange(value: Int) {
        val effort = EffortResolver.getTaskEffort(value)
        updateViewState(CreateTaskViewState.OnEffortChange(effort))
    }

    private fun getUserList() = listOf(
        User("12345", "Daniel", "danielrl.drl@gmail.com", null, weeklyEffort = 10),
        User("54321", "Pepe", "pepe@gmail.com", null, weeklyEffort = 15),
        User("34215", "Manuel", "manuel@gmail.com", null, weeklyEffort = 30)
    )

}