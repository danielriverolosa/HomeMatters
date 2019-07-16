package daniel.rivero.homematters.presentation.main.task.viewmodel

import com.prolificinteractive.materialcalendarview.CalendarDay
import daniel.rivero.homematters.domain.AssignedTask
import daniel.rivero.homematters.domain.interactor.task.list.AssignedTaskListToUserDto
import daniel.rivero.homematters.domain.interactor.task.list.GetAssignedTaskListToSingleUser
import daniel.rivero.homematters.domain.service.PreferenceService
import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.presentation.base.BaseViewModel
import daniel.rivero.homematters.presentation.main.task.event.TaskManagerEvent
import daniel.rivero.homematters.presentation.main.task.model.WeeklyData
import daniel.rivero.homematters.presentation.main.task.viewstate.TaskManagerViewState
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import java.util.*
import javax.inject.Inject

class TaskManagerViewModel @Inject constructor(
    app: AndroidApplication,
    val getAssignedTaskList: GetAssignedTaskListToSingleUser,
    val preferenceService: PreferenceService
): BaseViewModel<TaskManagerViewState, TaskManagerEvent>(app) {

    private lateinit var taskList: List<AssignedTask>

    override fun onEvent(event: TaskManagerEvent) {
        when(event) {
            TaskManagerEvent.Initialize -> loadData()
            TaskManagerEvent.AddTask -> navigator.showAddTaskSelectorMode()
        }
    }

    private fun loadData() {
        preferenceService.getUser()?.let {
            getAssignedTaskList(AssignedTaskListToUserDto(it.id, getLastDayOfMonth(), getFirstDayOfMonth()))
                .subscribe(::onSuccess, ::onError)
        }
    }

    private fun onSuccess(taskList: List<AssignedTask>) {
        this.taskList = taskList
        val completedTasks = taskList.filter { it.isDone }
        val pendingTasks = taskList.filter { !it.isDone }

        val expendedEffort: Int = completedTasks.sumBy { it.effort.value }
        val pendingEffort: Int = completedTasks.sumBy { it.effort.value }

        updateViewState(TaskManagerViewState.LoadData(WeeklyData(expendedEffort, pendingEffort, completedTasks, pendingTasks)))
    }

    private fun onError(t: Throwable) {
        updateViewState(TaskManagerViewState.ShowError(t.message))
    }

    private fun getFirstDayOfMonth(): Date {
        val cal = Calendar.getInstance()
        cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DAY_OF_MONTH))

        return cal.time
    }

    private fun getLastDayOfMonth(): Date {
        val cal = Calendar.getInstance()
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH))

        return cal.time
    }

    private fun convertToLocalDate(date: Date): LocalDate {
        return Instant.ofEpochMilli(date.time).atZone(ZoneId.systemDefault()).toLocalDate()
    }

}