package daniel.rivero.homematters.presentation.main.calendar.event

import com.prolificinteractive.materialcalendarview.CalendarDay
import daniel.rivero.homematters.domain.AssignedTask
import daniel.rivero.homematters.presentation.base.Event

sealed class CalendarEvent: Event {
    object Initialize: CalendarEvent()
    object ChangeCalendarMode: CalendarEvent()
    class OnClickDate(val date: CalendarDay): CalendarEvent()
    class OnClickItem(val task: AssignedTask) : CalendarEvent()
}