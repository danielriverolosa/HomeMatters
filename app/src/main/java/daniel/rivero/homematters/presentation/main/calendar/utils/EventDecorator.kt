package daniel.rivero.homematters.presentation.main.calendar.utils

import android.graphics.Color
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class EventDecorator(
    private val calendarEvents: List<CalendarDay>,
    private val color: Int = Color.RED
) : DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay?) = calendarEvents.contains(day)

    override fun decorate(view: DayViewFacade?) {
        view?.addSpan(DotSpan(5f, color))
    }

}