package daniel.rivero.homematters.data.datasource.api.stats

import daniel.rivero.homematters.data.datasource.api.stats.model.BarChartStatsRequest
import daniel.rivero.homematters.data.datasource.api.stats.model.BarChartStatsItemResponse
import io.reactivex.Single
import retrofit2.adapter.rxjava2.Result
import retrofit2.http.Body
import retrofit2.http.POST

interface StatsApi {
    @POST("/stats/barchart")
    fun getBarChartStats(@Body request: BarChartStatsRequest): Single<Result<List<BarChartStatsItemResponse>>>
}