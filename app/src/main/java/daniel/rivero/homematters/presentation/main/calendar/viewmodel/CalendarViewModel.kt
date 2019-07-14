package daniel.rivero.homematters.presentation.main.calendar.viewmodel

import com.prolificinteractive.materialcalendarview.CalendarDay
import daniel.rivero.homematters.domain.AssignedTask
import daniel.rivero.homematters.domain.Task
import daniel.rivero.homematters.domain.TaskEffort
import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.presentation.base.BaseViewModel
import daniel.rivero.homematters.presentation.main.calendar.event.CalendarEvent
import daniel.rivero.homematters.presentation.main.calendar.viewstate.CalendarViewState
import daniel.rivero.homematters.presentation.main.task.event.TaskListSelectorPageEvent
import org.threeten.bp.LocalDate
import java.util.*
import javax.inject.Inject

class CalendarViewModel @Inject constructor(
    app: AndroidApplication
) : BaseViewModel<CalendarViewState, CalendarEvent>(app) {

    private lateinit var taskList: List<Task>

    override fun onEvent(event: CalendarEvent) {
        when (event) {
            CalendarEvent.Initialize -> updateViewState(CalendarViewState.LoadInitialData(getRandomCalendarEvents(), getTaskList()))
            CalendarEvent.ChangeCalendarMode -> updateViewState(CalendarViewState.CalendarMode)
            is CalendarEvent.OnClickDate -> updateViewState(CalendarViewState.UpdateTaskList(getTaskList()))
            is CalendarEvent.OnClickItem -> navigator.showScheduleTask(event.task)
        }
    }

    private fun getRandomCalendarEvents(): List<CalendarDay> {
        var temp = LocalDate.now().minusMonths(2)
        val dates = ArrayList<CalendarDay>()
        for (i in 0..29) {
            val day = CalendarDay.from(temp)
            dates.add(day)
            temp = temp.plusDays(5)
        }

        return dates
    }

    private fun getTaskList() = listOf<AssignedTask>(
        AssignedTask("1", "Limpiar habitación", "ic_bedroom", TaskEffort.L, Date(), false),
        AssignedTask("1", "Limpiar baño", "ic_bathroom", TaskEffort.XXL, Date(), false),
        AssignedTask("1", "Comprar alimentos", "ic_grocery_shopping", TaskEffort.M, Date(), true),
        AssignedTask("1", "Barrer", "ic_sweep", TaskEffort.XS, Date(), false),
        AssignedTask("1", "Hacer la cena", "ic_cook_dinner", TaskEffort.M, Date(), true),
        AssignedTask("1", "Limpiar polvo", "ic_clean_dust", TaskEffort.S, Date(), false)
    )

}