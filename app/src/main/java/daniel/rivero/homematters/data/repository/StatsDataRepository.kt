package daniel.rivero.homematters.data.repository

import daniel.rivero.homematters.data.datasource.api.ApiClientGenerator
import daniel.rivero.homematters.data.repository.utils.ApiResponseHandler
import daniel.rivero.homematters.domain.EffortStat
import daniel.rivero.homematters.domain.interactor.chart.BarChartDto
import daniel.rivero.homematters.domain.repository.StatsRepository
import io.reactivex.Single
import javax.inject.Inject

class StatsDataRepository @Inject constructor(
    private val clientGenerator: ApiClientGenerator,
    private val apiResponseHandler: ApiResponseHandler
) : StatsRepository {

    override fun getStatsByHome(dto: BarChartDto): Single<List<EffortStat>> {
        return Single.just(listOf())
    }

}