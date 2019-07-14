package daniel.rivero.homematters.presentation.main.calendar.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.CalendarMode
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import daniel.rivero.homematters.R
import daniel.rivero.homematters.domain.AssignedTask
import daniel.rivero.homematters.infrastructure.ContentView
import daniel.rivero.homematters.infrastructure.di.component.ViewComponent
import daniel.rivero.homematters.presentation.base.BaseViewModelFragment
import daniel.rivero.homematters.presentation.main.calendar.adapter.TaskListAdapter
import daniel.rivero.homematters.presentation.main.calendar.event.CalendarEvent
import daniel.rivero.homematters.presentation.main.calendar.utils.EventDecorator
import daniel.rivero.homematters.presentation.main.calendar.viewmodel.CalendarViewModel
import daniel.rivero.homematters.presentation.main.calendar.viewstate.CalendarViewState
import kotlinx.android.synthetic.main.fragment_calendar.*

@ContentView(R.layout.fragment_calendar)
class CalendarFragment : BaseViewModelFragment<CalendarViewModel, CalendarViewState>(), OnDateSelectedListener {

    companion object {
        fun getInstance() = CalendarFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onEvent(CalendarEvent.Initialize)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    override fun initializeInjector(viewComponent: ViewComponent) {
        viewComponent.inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_calendar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_calendar_mode -> {
            viewModel.onEvent(CalendarEvent.ChangeCalendarMode)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val calendarItem = menu.findItem(R.id.action_calendar_mode)
        calendarItem.setIcon(getCalendarModeIcon(calendarView.calendarMode))
        super.onPrepareOptionsMenu(menu)
    }

    private fun getCalendarModeIcon(calendarMode: CalendarMode): Int {
        return when(calendarMode) {
            CalendarMode.MONTHS -> R.drawable.ic_calendar_weekly
            else -> R.drawable.ic_calendar_monthly
        }
    }

    override fun onDateSelected(widget: MaterialCalendarView, date: CalendarDay, selected: Boolean) {
        viewModel.onEvent(CalendarEvent.OnClickDate(date))
    }

    override fun render(viewState: CalendarViewState) {
        when (viewState) {
            is CalendarViewState.LoadInitialData -> loadData(viewState.calendarEvents, viewState.taskList)
            CalendarViewState.CalendarMode -> changeCalendarMode(calendarView.calendarMode)
            is CalendarViewState.UpdateTaskList -> loadAssignedTasks(viewState.taskList)
        }
    }

    private fun loadData(calendarEvents: List<CalendarDay>, taskList: List<AssignedTask>) {
        calendarView.addDecorator(
            EventDecorator(
                calendarEvents
            )
        )
        calendarView.setOnDateChangedListener(this)
        loadAssignedTasks(taskList)
    }

    private fun loadAssignedTasks(taskList: List<AssignedTask>) {
        val adapter = TaskListAdapter(taskList) { viewModel.onEvent(CalendarEvent.OnClickItem(it)) }
        recyclerView.adapter = adapter
    }

    private fun changeCalendarMode(calendarMode: CalendarMode) {
        val newCalendarMode = if (calendarMode == CalendarMode.MONTHS) CalendarMode.WEEKS else CalendarMode.MONTHS
        calendarView.state().edit().setCalendarDisplayMode(newCalendarMode).commit()
        activity?.invalidateOptionsMenu()
    }

}