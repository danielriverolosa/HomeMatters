package daniel.rivero.homematters.presentation.main.stats.viewstate

import daniel.rivero.homematters.domain.EffortStat
import daniel.rivero.homematters.presentation.base.ViewState

sealed class ChartViewState: ViewState {

    class LoadData(val stats: List<EffortStat>): ChartViewState()
    class ShowError(val message: String?) : ChartViewState()
    object ShowEmptyView: ChartViewState()

}