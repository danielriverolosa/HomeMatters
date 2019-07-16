package daniel.rivero.homematters.presentation.main.stats.viewmodel

import daniel.rivero.homematters.domain.EffortStat
import daniel.rivero.homematters.domain.interactor.chart.BarChartDto
import daniel.rivero.homematters.domain.interactor.chart.BarChartUseCase
import daniel.rivero.homematters.domain.service.PreferenceService
import daniel.rivero.homematters.infrastructure.AndroidApplication
import daniel.rivero.homematters.presentation.base.BaseViewModel
import daniel.rivero.homematters.presentation.main.stats.event.ChartEvent
import daniel.rivero.homematters.presentation.main.stats.viewstate.ChartViewState
import java.util.*
import javax.inject.Inject

class ChartViewModel @Inject constructor(
    app: AndroidApplication,
    val preferenceService: PreferenceService,
    val useCase: BarChartUseCase
): BaseViewModel<ChartViewState, ChartEvent>(app) {

    override fun onEvent(event: ChartEvent) {
        when(event) {
            ChartEvent.Initialize -> loadChartData()
        }
    }

    private fun loadChartData() {
        preferenceService.getDefaultHome()?.let {
            useCase(BarChartDto(it))
                .subscribe(::onSuccess, ::onError)
        } ?: updateViewState(ChartViewState.ShowEmptyView)
    }

    private fun onSuccess(stats: List<EffortStat>) {
        updateViewState(ChartViewState.LoadData(stats))
    }

    private fun onError(t: Throwable) {
        updateViewState(ChartViewState.ShowError(t.message))
    }

}