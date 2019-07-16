package daniel.rivero.homematters.presentation.main.calendar.viewmodel

import com.prolificinteractive.materialcalendarview.CalendarDay
import daniel.rivero.homematters.domain.AssignedTask
import daniel.rivero.homematters.domain.Task
import daniel.rivero.homematters.domain.TaskEffort
import daniel.rivero.homematters.domain.interactor.task.list.AssignedTaskListToUserDto
import daniel.rivero.homematters.domain.interactor.task.list.GetAssignedTaskListToSingleUser
import daniel.rivero.homematters.domain.service.PreferenceService
import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.presentation.base.BaseViewModel
import daniel.rivero.homematters.presentation.main.calendar.event.CalendarEvent
import daniel.rivero.homematters.presentation.main.calendar.viewstate.CalendarViewState
import daniel.rivero.homematters.presentation.main.task.event.TaskListSelectorPageEvent
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import java.util.*
import javax.inject.Inject

class CalendarViewModel @Inject constructor(
    app: AndroidApplication,
    val getAssignedTaskList: GetAssignedTaskListToSingleUser,
    val preferenceService: PreferenceService
) : BaseViewModel<CalendarViewState, CalendarEvent>(app) {

    private lateinit var taskList: List<AssignedTask>

    override fun onEvent(event: CalendarEvent) {
        when (event) {
            CalendarEvent.Initialize -> getAssignedTasks()
            CalendarEvent.ChangeCalendarMode -> updateViewState(CalendarViewState.CalendarMode)
            is CalendarEvent.OnClickDate -> onClickCalendarDay(event.date)
            is CalendarEvent.OnClickItem -> navigator.showScheduleTask(event.task)
        }
    }

    private fun getAssignedTasks() {
        preferenceService.getUser()?.let {
            getAssignedTaskList(AssignedTaskListToUserDto(it.id, getLastDayOfMonth(), getFirstDayOfMonth()))
                .subscribe(::onSuccess, ::onError)
        }
    }

    private fun onSuccess(taskList: List<AssignedTask>) {
        this.taskList = taskList
        updateViewState(CalendarViewState.LoadInitialData(
            taskList.map { CalendarDay.from(convertToLocalDate(it.date)) }, taskList)
        )
    }

    private fun onError(t: Throwable) {
        updateViewState(CalendarViewState.ShowError(t.message))
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

    private fun onClickCalendarDay(day: CalendarDay) {
        val filterList = taskList.filter {
            val localDate = convertToLocalDate(it.date)
            val calendarDay = CalendarDay.from(localDate)
            day.day == calendarDay.day
        }

        updateViewState(CalendarViewState.UpdateTaskList(filterList))
    }

}