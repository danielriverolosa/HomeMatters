package daniel.rivero.homematters.data.repository

import daniel.rivero.homematters.data.datasource.api.ApiClientGenerator
import daniel.rivero.homematters.data.datasource.api.stats.StatsApi
import daniel.rivero.homematters.data.datasource.api.stats.model.BarChartStatsRequest
import daniel.rivero.homematters.data.datasource.api.stats.model.RangeDateRequest
import daniel.rivero.homematters.data.repository.utils.ApiResponseHandler
import daniel.rivero.homematters.domain.EffortStat
import daniel.rivero.homematters.domain.interactor.chart.BarChartDto
import daniel.rivero.homematters.domain.repository.StatsRepository
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class StatsDataRepository @Inject constructor(
    private val clientGenerator: ApiClientGenerator,
    private val apiResponseHandler: ApiResponseHandler
) : StatsRepository {

    private val api by lazy { clientGenerator.generateApi(StatsApi::class) }

    override fun getStatsByHome(dto: BarChartDto): Single<List<EffortStat>> {
        val response = api.getBarChartStats(BarChartStatsRequest(dto.home.id, getRangeList()))

        return apiResponseHandler.processResponse(response).map {
            it.map { effortData -> EffortStat(Date(effortData.date), effortData.totalEffort) }
        }
    }

    private fun getRangeList(): List<RangeDateRequest> {
        val rangeList = mutableListOf<RangeDateRequest>()
        for (i in 0 downTo -5) {
            rangeList += buildRangeDateRequest(i)
        }
        return rangeList
    }

    private fun buildRangeDateRequest(monthLess: Int) = RangeDateRequest(
        getFirstDayOfMonth(monthLess).time,
        getLastDayOfMonth(monthLess).time
    )

    private fun getFirstDayOfMonth(monthLess: Int): Date {
        val cal = Calendar.getInstance()
        cal.add(Calendar.MONTH, monthLess)
        cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DAY_OF_MONTH))

        return cal.time
    }

    private fun getLastDayOfMonth(monthLess: Int): Date {
        val cal = Calendar.getInstance()
        cal.add(Calendar.MONTH, monthLess)
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DAY_OF_MONTH))

        return cal.time
    }

}