package daniel.rivero.homematters.domain.repository

import daniel.rivero.homematters.domain.EffortStat
import daniel.rivero.homematters.domain.interactor.chart.BarChartDto
import io.reactivex.Single

interface StatsRepository {

    fun getStatsByHome(dto: BarChartDto): Single<List<EffortStat>>

}