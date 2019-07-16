package daniel.rivero.homematters.presentation.main.calendar.viewstate

import com.prolificinteractive.materialcalendarview.CalendarDay
import daniel.rivero.homematters.domain.AssignedTask
import daniel.rivero.homematters.presentation.base.ViewState

sealed class CalendarViewState: ViewState {
    class LoadInitialData(val calendarEvents: List<CalendarDay>, val taskList: List<AssignedTask>): CalendarViewState()
    object CalendarMode: CalendarViewState()
    class UpdateTaskList(val taskList: List<AssignedTask>): CalendarViewState()
    class ShowError(val message: String?): CalendarViewState()
}