package daniel.rivero.homematters.presentation.main.stats.viewmodel

import daniel.rivero.homematters.domain.EffortStat
import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.presentation.base.BaseViewModel
import daniel.rivero.homematters.presentation.main.stats.event.ChartEvent
import daniel.rivero.homematters.presentation.main.stats.viewstate.ChartViewState
import java.util.*
import javax.inject.Inject

class ChartViewModel @Inject constructor(
    app: AndroidApplication
): BaseViewModel<ChartViewState, ChartEvent>(app) {

    override fun onEvent(event: ChartEvent) {
        when(event) {
            ChartEvent.Initialize -> loadChartData()
        }
    }

    private fun loadChartData() {
        updateViewState(ChartViewState.LoadData(getStats()))
    }

    private fun getStats() = listOf(
        EffortStat(getDate(-1), getDate(-1), 90),
        EffortStat(getDate(-2), getDate(-2), 45),
        EffortStat(getDate(-3), getDate(-3), 70),
        EffortStat(getDate(-4), getDate(-4), 120),
        EffortStat(getDate(-5), getDate(-5), 35),
        EffortStat(getDate(-6), getDate(-6), 95)
    )

    private fun getDate(monthLess: Int) : Date {
        val cal = Calendar.getInstance()
        cal.add(Calendar.MONTH, monthLess)

        return cal.time
    }

}