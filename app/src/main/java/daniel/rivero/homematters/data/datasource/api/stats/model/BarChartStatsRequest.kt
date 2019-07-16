package daniel.rivero.homematters.data.datasource.api.stats.model

class BarChartStatsRequest(
    val houseId: String,
    val dates: List<RangeDateRequest>
)